package az.ingress.model.request;

import static az.ingress.model.constant.ApplicationConstant.MIN_PRICE;
import static az.ingress.model.constant.ValidationMessages.EMPTY_PRODUCT_VARIANT_ID;
import static az.ingress.model.constant.ValidationMessages.INVALID_PRICE;
import static az.ingress.model.constant.ValidationMessages.NULL_PRICE;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

@Data
public class ProductVariantValidationRequest {

  @NotEmpty(message = EMPTY_PRODUCT_VARIANT_ID)
  private List<Long> productVariantIds;

  @NotNull(message = NULL_PRICE)
  @DecimalMin(value = MIN_PRICE, message = INVALID_PRICE)
  private BigDecimal totalPrice;

}
