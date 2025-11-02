package az.ingress.model.response;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductValidationErrorResponse {

  private String errorCode;
  private String message;
  private List<ProductIssueResponse> productIssueResponses;
  private Boolean hasOutOfStock;
  private Boolean hasPriceChanged;

}
