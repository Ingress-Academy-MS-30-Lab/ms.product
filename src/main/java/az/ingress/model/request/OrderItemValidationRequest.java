package az.ingress.model.request;

import static az.ingress.model.constant.ValidationMessages.EMPTY_PRODUCT_ID;
import static az.ingress.model.constant.ValidationMessages.EMPTY_PRODUCT_VARIANT_ID;
import static az.ingress.model.constant.ValidationMessages.EMPTY_QUANTITY;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderItemValidationRequest {
  @NotNull(message = EMPTY_PRODUCT_ID)
  private Long productId;

  @NotNull(message = EMPTY_PRODUCT_VARIANT_ID)
  private Long variantId;

  @NotNull(message = EMPTY_QUANTITY)
  @Min(value = 1)
  private Long quantity;

}
