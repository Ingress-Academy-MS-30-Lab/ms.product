package az.ingress.exception;

import static az.ingress.exception.ErrorMessage.MAX_QUANTITY_EXCEEDED;

public class MaxQuantityExceededException extends BaseException {

  public MaxQuantityExceededException(String message) {
    super(MAX_QUANTITY_EXCEEDED.name(), message);
  }

}
