package az.ingress.service.abstracts;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Map;
import az.ingress.dao.entities.ProductEntity;
import az.ingress.model.request.OrderItemsValidationRequest;
import az.ingress.model.request.ReservationRequest;
import az.ingress.model.response.OrderValidationResponse;
import az.ingress.model.response.PageResponse;
import az.ingress.model.response.ProductPreviewResponse;
import az.ingress.model.response.ReservationResponse;

public interface ProductService {

  PageResponse<ProductPreviewResponse> getProductsByIds(@NotEmpty List<Long> ids, int page, int size);

  void checkProductExists(Long id);

  OrderValidationResponse validateProducts(OrderItemsValidationRequest validationRequest);

  ReservationResponse reserveProducts(@Valid ReservationRequest reservationRequest);

  void deductStock(Map<ProductEntity, Long> productsWithQty);

  void releaseStock(Map<ProductEntity, Long> productsWithQty);

  List<ProductEntity> getProductsByIdsForUpdate(List<Long> ids);

}
