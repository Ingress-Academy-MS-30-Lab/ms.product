package az.ingress.model.event;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import az.ingress.model.response.ProductVariantResponse;
import lombok.Data;

@Data
public class ApprovedProductEventModel {

  private Long productId;
  private String title;
  private String description;
  private Long categoryId;
  private String categoryName;
  private BigDecimal basePrice;
  private Long createdBy;
  private String createdByUsername;
  private LocalDateTime createdAt;
  private Long approvedBy;
  private String approvedByUsername;
  private LocalDateTime approvedAt;
  private List<ProductVariantResponse> productVariants;

}
