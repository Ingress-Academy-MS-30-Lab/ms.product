package az.ingress.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseException extends RuntimeException {

  private String code;

  public BaseException(String code, String message) {
    super(message);
    this.code = code;
  }

}
