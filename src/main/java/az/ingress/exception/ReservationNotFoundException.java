package az.ingress.exception;

import static az.ingress.exception.ErrorMessage.RESERVATION_NOT_FOUND;

public class ReservationNotFoundException extends BaseException {

  public ReservationNotFoundException(String message) {
    super(RESERVATION_NOT_FOUND.name(), message);
  }

}
