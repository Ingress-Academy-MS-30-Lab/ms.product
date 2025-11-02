package az.ingress.exception;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {

  private final String errorCode;
  private final String message;


  public BaseException(String errorCode, String message) {
    this.errorCode = errorCode;
    this.message = message;
  }

}
