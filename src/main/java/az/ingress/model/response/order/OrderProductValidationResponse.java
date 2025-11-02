package az.ingress.model.response.order;

import java.util.List;
import lombok.Data;

@Data
public class OrderProductValidationResponse {

  private Long productId;
  private Long categoryId;
  private String title;
  private List<OrderProductResponse> productVariants;
  private Long totalQuantity;

}
