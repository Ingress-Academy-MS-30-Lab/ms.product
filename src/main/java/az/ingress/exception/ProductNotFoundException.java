package az.ingress.exception;

import lombok.Getter;

@Getter
public class ProductNotFoundException extends BaseException {

  public ProductNotFoundException(String errorCode, String message) {
    super(errorCode, message);
  }

}
