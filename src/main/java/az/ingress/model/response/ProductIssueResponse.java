package az.ingress.model.response;

import java.math.BigDecimal;
import az.ingress.model.enums.ProductIssueType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductIssueResponse {

  private ProductIssueType issueType;
  private String message;
  private Long productGroupId;
  private Long productId;
  private String title;
  private Long requestedQuantity;
  private Integer availableQuantity;
  private BigDecimal requestedPrice;
  private BigDecimal currentPrice;

}
