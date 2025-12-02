package az.ingress.service.concrete;

import static az.ingress.exception.ErrorMessage.RESERVATION_NOT_FOUND;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import az.ingress.annotation.Logable;
import az.ingress.dao.entities.ProductEntity;
import az.ingress.dao.entities.ReservationEntity;
import az.ingress.dao.repositories.ProductReservationRepository;
import az.ingress.exception.ReservationNotFoundException;
import az.ingress.model.enums.OrderStatus;
import az.ingress.model.enums.ReservationStatus;
import az.ingress.model.request.ProductQuantityRequest;
import az.ingress.model.request.StockUpdateCallbackRequest;
import az.ingress.service.abstracts.InventoryService;
import az.ingress.service.abstracts.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Logable
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

  private final ProductReservationRepository productReservationRepository;
  private final ProductService productService;

  @Override
  @Transactional
  public void releaseStock(StockUpdateCallbackRequest request) {
    var reservation = fetchReservation(request.getReservationId(), request.getOrderId());
    var products = productService.getProductsByIdsForUpdate(
        reservation.getReservationProducts()
            .stream().map(pr -> pr.getProduct().getId()).toList()
    );
    var productsWithQty = mapReservationProducts(products, request.getItems());
    productService.releaseStock(productsWithQty);
    reservation.setReservationStatus(ReservationStatus.CANCELLED);
    reservation.setOrderStatus(OrderStatus.FAILED);
  }

  @Override
  @Transactional
  public void deductStock(StockUpdateCallbackRequest request) {
    var reservation = fetchReservation(
        request.getReservationId(), request.getOrderId()
    );
    var products = productService.getProductsByIdsForUpdate(reservation.getReservationProducts()
        .stream().map(pr -> pr.getProduct().getId()).toList());
    productService.deductStock(mapReservationProducts(products, request.getItems()));
    reservation.setOrderStatus(request.getStatus());
    reservation.setReservationStatus(ReservationStatus.COMPLETED);
  }

  private ReservationEntity fetchReservation(String reservationId, Long orderId) {
    return productReservationRepository.findByUuidAndOrderId(reservationId, orderId)
        .orElseThrow(() -> new ReservationNotFoundException(RESERVATION_NOT_FOUND.getValue()));

  }

  private Map<ProductEntity, Long> mapReservationProducts(
      List<ProductEntity> products,
      List<ProductQuantityRequest> items
  ) {
    Map<Long, Long> qtyMap = InventoryServiceImpl.toProductQuantityMap(items);
    return products.stream()
        .filter(p -> qtyMap.containsKey(p.getId()))
        .collect(Collectors.toMap(p -> p, p -> qtyMap.get(p.getId())));
  }

  public static Map<Long, Long> toProductQuantityMap(List<ProductQuantityRequest> items) {
    return items.stream()
        .collect(Collectors.toMap(
            ProductQuantityRequest::getProductId,
            ProductQuantityRequest::getQuantity
        ));
  }

}
