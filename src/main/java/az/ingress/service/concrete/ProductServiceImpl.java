package az.ingress.service.concrete;

import static az.ingress.exception.ErrorMessage.PRODUCT_NOT_FOUND;
import static az.ingress.mapper.ProductMapper.PRODUCT_MAPPER;
import static az.ingress.model.enums.ProductAttributeType.VARIANT;
import static java.util.Objects.nonNull;

import java.util.List;
import java.util.Optional;
import az.ingress.dao.entities.ProductEntity;
import az.ingress.dao.repositories.ProductRepository;
import az.ingress.exception.ProductNotFoundException;
import az.ingress.model.request.OrderItemsValidationRequest;
import az.ingress.model.request.ProductFilterRequest;
import az.ingress.model.request.ReservationRequest;
import az.ingress.model.response.CartResponse;
import az.ingress.model.response.PageResponse;
import az.ingress.model.response.ProductDetailsResponse;
import az.ingress.model.response.ProductPreviewResponse;
import az.ingress.model.response.ReservationResponse;
import az.ingress.model.response.order.OrderValidationResponse;
import az.ingress.model.request.StockUpdateRequest;
import az.ingress.service.abstraction.ProductAttributeValueService;
import az.ingress.service.abstraction.ProductImageService;
import az.ingress.service.abstraction.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;
  private final ProductImageService productImageService;
  private final ProductAttributeValueService productAttributeValueService;

  public void checkProductExists(Long id) {
    //TODO
  }

  public OrderValidationResponse validateProducts(OrderItemsValidationRequest validationRequest) {
    //TODO
    return null;
  }

  public ReservationResponse reserveProductVariants(
      ReservationRequest reservationRequest
  ) {
    //TODO
    return null;
  }

  public void updateStock(StockUpdateRequest stockUpdateRequest) {
    //TODO
  }

  public List<CartResponse> getProductsForCart(List<Long> ids) {
    var products = productRepository.findAllByIds(ids);
    return PRODUCT_MAPPER.mapToProductCartResponseList(products);
  }

  @Override
  public PageResponse<ProductPreviewResponse> getTopRatedProducts() {
    //TODO
    return null;
  }

  @Override
  public PageResponse<ProductPreviewResponse> getProductByCategory(List<Long> categoryIds) {
    //TODO
    return null;
  }

  @Override
  public PageResponse<ProductPreviewResponse> getProducts(Optional<Long> categoryId,
                                                          int page, int size, String sort,
                                                          ProductFilterRequest filterRequest) {
    var pageRequest = PageRequest.of(page, size, Sort.by(sort));

    Page<ProductEntity> result = categoryId
        .map(id -> productRepository.findAllByCategoryId(id, pageRequest))
        .orElseGet(()-> productRepository.findAll(pageRequest));

    return null;
//    return PRODUCT_MAPPER.mapToPageProductDetailsResponse(result);
  }

  @Override
  public ProductDetailsResponse getProductById(Long id) {
    var product = getProductOverview(id);
    var productImages = productImageService.getProductImagesByProductId(id);
    var productAttributes = productAttributeValueService.getProductAttributesByProductId(id);
    var variantAttributeIds = productAttributes
        .stream()
        .filter(
            pav ->
                nonNull(pav.getAttribute()) && VARIANT.equals(pav.getAttribute().getType())
        )
        .map(pav -> pav.getAttribute().getId())
        .toList();
    var otherVariants = productRepository.findProductVariantsByAttribute(variantAttributeIds);
    //list bosh olarsa handle etmeyi?
    return PRODUCT_MAPPER
        .mapToProductDetailsResponse(
            product, productImages, productAttributes, otherVariants, id
        );
  }

  public ProductEntity getProductOverview(Long id) {
    return productRepository.findBaseInfoById(id)
        .orElseThrow(() ->
            new ProductNotFoundException(PRODUCT_NOT_FOUND.name(), PRODUCT_NOT_FOUND.getValue())
        );
  }

}