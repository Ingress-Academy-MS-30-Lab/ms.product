package az.ingress.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {

  PRODUCT_VALIDATION_FAILED("Product validation failed: either price changed or product is out of stock"),
  PRODUCT_NOT_FOUND("Product not found"),
  PRODUCT_VARIANT_NOT_FOUND("Product variant not found"),
  RESERVATION_NOT_FOUND("Reservation not found"),
  UNEXPECTED_ERROR("Unexpected error occurred");

  private final String value;
}