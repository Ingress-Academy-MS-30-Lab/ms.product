package az.ingress.exception;

import java.util.List;
import az.ingress.model.response.ProductIssueResponse;
import lombok.Getter;

@Getter
public class ProductValidationException extends BaseException {

  private final List<ProductIssueResponse> issues;

  public ProductValidationException(String errorCode,
                                    String message,
                                    List<ProductIssueResponse> issues
  ) {
    super(errorCode, message);
    this.issues = issues;
  }

}
