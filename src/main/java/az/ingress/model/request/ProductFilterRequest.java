package az.ingress.model.request;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

@Data
public class ProductFilterRequest {

  private String name;
  private String description;
  private String brand;
  private List<Long> categoryIds;
  private BigDecimal minPrice;
  private BigDecimal maxPrice;
  private List<AttributeFilter> attributes;

}
