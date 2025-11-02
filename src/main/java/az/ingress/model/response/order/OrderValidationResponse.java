package az.ingress.model.response.order;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

@Data
public class OrderValidationResponse {

  private List<OrderProductValidationResponse> products;
  private BigDecimal totalPrice;

}
