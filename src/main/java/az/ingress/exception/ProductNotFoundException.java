package az.ingress.exception;

public class ProductNotFoundException extends BaseException {

  public ProductNotFoundException(String code, String message) {
    super(code, message);
  }

}
