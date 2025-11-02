package az.ingress.model.response;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class PriceResponse {

  private BigDecimal price;
  private BigDecimal salePrice;
  private Long discountPercentage;

}
