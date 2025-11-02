package az.ingress.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VariantOption {

  private String value;
  private Long productId;
  private boolean inStock;
  private boolean isSelected;

}