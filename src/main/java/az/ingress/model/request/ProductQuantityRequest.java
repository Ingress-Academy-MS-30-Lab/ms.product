package az.ingress.model.request;

import static az.ingress.model.constant.ValidationMessages.EMPTY_PRODUCT_ID;
import static az.ingress.model.constant.ValidationMessages.EMPTY_QUANTITY;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductQuantityRequest {

  @NotNull(message = EMPTY_PRODUCT_ID)
  private Long productId;

  @NotNull(message = EMPTY_QUANTITY)
  private Long quantity;

}
