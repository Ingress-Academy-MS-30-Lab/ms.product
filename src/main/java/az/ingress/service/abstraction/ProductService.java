package az.ingress.service.abstraction;

import java.util.List;
import java.util.Optional;
import az.ingress.model.request.OrderItemsValidationRequest;
import az.ingress.model.request.ProductFilterRequest;
import az.ingress.model.request.ReservationRequest;
import az.ingress.model.request.StockUpdateRequest;
import az.ingress.model.response.CartResponse;
import az.ingress.model.response.PageResponse;
import az.ingress.model.response.ProductDetailsResponse;
import az.ingress.model.response.ProductPreviewResponse;
import az.ingress.model.response.ReservationResponse;
import az.ingress.model.response.order.OrderValidationResponse;

public interface ProductService {

  void checkProductExists(Long id);

  OrderValidationResponse validateProducts(OrderItemsValidationRequest validationRequest);


  ReservationResponse reserveProductVariants(
      ReservationRequest reservationRequest
  );

  void updateStock(StockUpdateRequest stockUpdateRequest);

  List<CartResponse> getProductsForCart(List<Long> ids);

  PageResponse<ProductPreviewResponse> getTopRatedProducts();

  PageResponse<ProductPreviewResponse> getProductByCategory(List<Long> categoryIds);


  PageResponse<ProductPreviewResponse> getProducts(Optional<Long> categoryId,
                                                   int page, int size, String sort,
                                                   ProductFilterRequest filterRequest);

  ProductDetailsResponse getProductById(Long id);

}
