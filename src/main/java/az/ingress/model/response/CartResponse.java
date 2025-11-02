package az.ingress.model.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartResponse {

  private Long productGroupId;
  private Long supplierId;
  private String supplierUsername;
  private Long categoryId;
  private String categoryName;
  private String title;
  private List<CartProductResponse> products;

}
