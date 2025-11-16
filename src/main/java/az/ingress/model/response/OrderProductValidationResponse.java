package az.ingress.model.response;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderProductValidationResponse {

  private Long productId;
  private BigDecimal price;
  private BigDecimal salePrice;
  private Long requestedQuantity;

}