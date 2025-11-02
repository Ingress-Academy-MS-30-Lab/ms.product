package az.ingress.model.response;

import java.math.BigDecimal;

public class ProductVariantReservationResponse {

  private Long id;
  private BigDecimal price;
  private Boolean onSale;
  private BigDecimal salePrice;
  private Long requestedQuantity;
  private Boolean available;

}

//"productVariants": [
//        {
//          "id": 2,
//          "price": 1989.0,
//          "onSale": true,
//          "salePrice": 1089.5,
//          "requestedQuantity": 2,
//          "inStock": true,
//          "available": true
//        }
//      ]