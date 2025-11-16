package az.ingress.exception;

import static az.ingress.exception.ErrorMessage.OUT_OF_STOCK;

public class OutOfStockException extends BaseException{

  public OutOfStockException(String message) {
    super(OUT_OF_STOCK.name(), message);
  }

}
