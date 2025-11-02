package az.ingress.model.response.order;

import az.ingress.model.response.PriceResponse;
import lombok.Data;

@Data
public class OrderProductResponse {

  private Long variantId;
  private String imageUrl;
  private PriceResponse price;
  private Long requestedQuantity;

}
