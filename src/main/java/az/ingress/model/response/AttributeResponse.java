package az.ingress.model.response;

import az.ingress.model.enums.ProductAttributeType;
import lombok.Data;

@Data
public class AttributeResponse {

  private Long id;
  private String name;
  private String value;
  private ProductAttributeType type;

}
