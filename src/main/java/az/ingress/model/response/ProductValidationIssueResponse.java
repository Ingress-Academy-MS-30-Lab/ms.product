package az.ingress.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductValidationIssueResponse {

  private Long productId;
  private Long productVariantId;
  private String productName;
  private Long requestedQuantity;
  private Boolean outOfStock;

}
