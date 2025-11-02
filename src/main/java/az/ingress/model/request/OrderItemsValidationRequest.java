package az.ingress.model.request;

import javax.validation.constraints.NotNull;
import java.util.List;
import lombok.Data;

@Data
public class OrderItemsValidationRequest {

  @NotNull
  private Long userId;
  private List<OrderItemValidationRequest> items;

}
