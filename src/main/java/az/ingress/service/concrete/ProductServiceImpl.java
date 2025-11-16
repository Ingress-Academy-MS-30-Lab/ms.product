package az.ingress.service.concrete;

import static az.ingress.exception.ErrorMessage.OUT_OF_STOCK;
import static az.ingress.exception.ErrorMessage.PRODUCT_NOT_FOUND;
import static az.ingress.exception.ErrorMessage.MAX_QUANTITY_EXCEEDED;
import static az.ingress.mapper.ProductMapper.PRODUCT_MAPPER;
import static az.ingress.mapper.ProductReservationMapper.PRODUCT_RESERVATION_MAPPER;
import static az.ingress.service.concrete.InventoryServiceImpl.toProductQuantityMap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import az.ingress.config.ProductReservationProperties;
import az.ingress.dao.entities.ProductEntity;
import az.ingress.dao.entities.ProductReservationEntity;
import az.ingress.dao.entities.ReservationEntity;
import az.ingress.dao.repositories.ProductRepository;
import az.ingress.dao.repositories.ProductReservationRepository;
import az.ingress.exception.MaxQuantityExceededException;
import az.ingress.exception.OutOfStockException;
import az.ingress.exception.ProductNotFoundException;
import az.ingress.model.enums.ReservationStatus;
import az.ingress.model.request.OrderItemsValidationRequest;
import az.ingress.model.request.ReservationRequest;
import az.ingress.model.response.OrderProductValidationResponse;
import az.ingress.model.response.OrderValidationResponse;
import az.ingress.model.response.PageResponse;
import az.ingress.model.response.ProductPreviewResponse;
import az.ingress.model.response.ReservationResponse;
import az.ingress.service.abstracts.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;
  private final ProductReservationRepository productReservationRepository;
  private final ProductReservationProperties reservationProperties;

  @Override
  public PageResponse<ProductPreviewResponse> getProductsByIds(List<Long> ids, int page, int size) {
    var pageRequest = PageRequest.of(page, size);
    var pageResponse = productRepository.findByIdsIn(ids, pageRequest);
    return PRODUCT_MAPPER.mapToProductPreviewPageResponse(pageResponse);
  }

  @Override
  public void checkProductExists(Long id) {
    if (!productRepository.existsById(id)) {
      throw new ProductNotFoundException(PRODUCT_NOT_FOUND.name(), PRODUCT_NOT_FOUND.getValue());
    }
  }

  @Override
  @Transactional
  public OrderValidationResponse validateProducts(OrderItemsValidationRequest validationRequest) {
    var productIdsWithQty = toProductQuantityMap(validationRequest.getProducts());
    var products = productRepository.findByIdIn(productIdsWithQty.keySet());
    List<OrderProductValidationResponse> validatedItems = new ArrayList<>();
    var totalPrice = BigDecimal.ZERO;
    for (var product : products) {
      var qty = productIdsWithQty.get(product.getId());
      validateProduct(product, qty);
      var unitPrice = product.getSalePrice() != null
          ? product.getSalePrice()
          : product.getPrice();
      var itemTotalPrice = unitPrice.multiply(BigDecimal.valueOf(qty));
      validatedItems.add(
          OrderProductValidationResponse.builder()
              .productId(product.getId())
              .price(product.getPrice())
              .salePrice(product.getSalePrice())
              .requestedQuantity(qty)
              .build()
      );
      totalPrice = totalPrice.add(itemTotalPrice);
    }
    return OrderValidationResponse.builder()
        .products(validatedItems)
        .totalPrice(totalPrice)
        .build();
  }

  @Override
  @Transactional
  public ReservationResponse reserveProducts(ReservationRequest reservationRequest) {
    var productReservation = PRODUCT_RESERVATION_MAPPER.mapToReservation(reservationRequest);
    productReservation
        .setExpiresAt(productReservation.getReservedAt().plusMinutes(reservationProperties.getExpiresAfterMinutes()));
    var productIdsWithQty = toProductQuantityMap(reservationRequest.getProducts());
    var productsToReserve = productRepository.findByIdIn(productIdsWithQty.keySet());
    var productReservations = createProductReservations(productsToReserve, productIdsWithQty, productReservation);
    productReservation.setReservationProducts(productReservations);
    productReservation.setReservationStatus(ReservationStatus.ACTIVE);
    productReservationRepository.save(productReservation);
    return ReservationResponse.builder()
        .reservationId(productReservation.getUuid())
        .status(productReservation.getReservationStatus())
        .expiresAt(productReservation.getExpiresAt())
        .build();
  }

  private List<ProductReservationEntity> createProductReservations(
      List<ProductEntity> productsToReserve,
      Map<Long, Long> productIdsWithQty,
      ReservationEntity reservation
  ) {

    return productsToReserve.stream().map(productToReserve -> {
      Long qty = productIdsWithQty.get(productToReserve.getId());
      reserveProduct(productToReserve, qty);
      return ProductReservationEntity.builder()
          .product(productToReserve)
          .reservation(reservation)
          .quantity(qty)
          .build();
    }).toList();

  }

  private void reserveProduct(ProductEntity productToReserve, Long qty) {
    validateProduct(productToReserve, qty);
    productToReserve.setReservedQuantity(productToReserve.getReservedQuantity() + qty);
    productToReserve.setAvailableQuantity(productToReserve.getAvailableQuantity() - qty);
  }

  private void validateProduct(ProductEntity productToReserve, Long qty) {
    if (productToReserve.getAvailableQuantity() < qty) {
      throw new OutOfStockException(OUT_OF_STOCK.getValue());
    }
    if (qty > reservationProperties.getMaxQuantity()) {
      throw new MaxQuantityExceededException(MAX_QUANTITY_EXCEEDED.getValue());
    }
  }

  @Transactional
  public void deductStock(Map<ProductEntity, Long> productsWithQty) {
    productsWithQty.forEach((product, qty) -> {
      long newStock = Math.max(0, product.getStockQuantity() - qty);
      long newReserved = Math.max(0, product.getReservedQuantity() - qty);
      product.setStockQuantity(newStock);
      product.setReservedQuantity(newReserved);
      if (newStock == 0) product.setInStock(false);
    });
  }

  @Override
  @Transactional
  public void releaseStock(Map<ProductEntity, Long> productsWithQty) {
    productsWithQty.forEach((product, qty) -> {
      product.setReservedQuantity(Math.max(0, product.getReservedQuantity() - qty));
      product.setAvailableQuantity(product.getAvailableQuantity() + qty);
    });
  }

  @Override
  public List<ProductEntity> getProductsByIdsForUpdate(List<Long> ids) {
    return productRepository.findByIdIn(ids);
  }

}
