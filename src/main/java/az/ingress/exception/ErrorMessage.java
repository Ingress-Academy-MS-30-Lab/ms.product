package az.ingress.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {
  UNEXPECTED_ERROR("Unexpected error occurred"),
  PRODUCT_NOT_FOUND("Product not found"),
  RESERVATION_NOT_FOUND("Reservation not found"),
  VALIDATION_ERROR("Validation error"),
  OUT_OF_STOCK("Some product are out of stock!"),
  MAX_QUANTITY_EXCEEDED("Max quantity exceeded");

  private final String value;
}