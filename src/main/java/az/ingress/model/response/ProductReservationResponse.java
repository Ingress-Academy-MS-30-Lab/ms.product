package az.ingress.model.response;

import java.util.List;
import lombok.Data;

@Data
public class ProductReservationResponse {

  private Long id;
  private String title;
  private List<ProductVariantReservationResponse> products;

}


//  "products": [
//    {
//      "id": 2,
//      "title": "iPhone 17",
//
//    }
//  ],