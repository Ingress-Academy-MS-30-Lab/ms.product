package az.ingress.model.response;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

@Data
public class ProductVariantResponse {

  private Long id;
  private List<ImageResponse> images;
  private BigDecimal price;
  private Boolean onStock;
  private Integer stockQuantity;
  private List<AttributeResponse> attributes;

}