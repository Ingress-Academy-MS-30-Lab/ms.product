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
public class CartProductResponse {

  private Long productId;
  private String imageUrl;
//  private PriceResponse price;
  private Long stockQuantity;
  private Boolean inStock;
  private List<AttributeResponse> attributes;

}
