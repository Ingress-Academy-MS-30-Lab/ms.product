package az.ingress.model.response;

import java.math.BigDecimal;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderValidationResponse {

  private BigDecimal totalPrice;
  private List<OrderProductValidationResponse> products;

}