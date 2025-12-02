package az.ingress.exception;

import static az.ingress.exception.ErrorMessage.UNEXPECTED_ERROR;
import static az.ingress.exception.ErrorMessage.VALIDATION_ERROR;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

  @ExceptionHandler(Exception.class)
  @ResponseStatus(INTERNAL_SERVER_ERROR)
  public ErrorResponse handle(Exception ex) {
    log.error("Exception: ", ex);
    return ErrorResponse.builder()
        .code(UNEXPECTED_ERROR.name())
        .message(UNEXPECTED_ERROR.getValue())
        .build();
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  @ResponseStatus(METHOD_NOT_ALLOWED)
  public ErrorResponse handle(HttpRequestMethodNotSupportedException ex) {
    log.error("HttpRequestMethodNotSupportedException: ", ex);
    return ErrorResponse.builder()
        .message(ex.getMessage())
        .build();
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ErrorResponse handle(MethodArgumentNotValidException e) {

    List<ValidationErrorResponse> fieldErrors = Optional.of(e.getBindingResult())
        .map(result -> result.getFieldErrors().stream()
            .map(fieldError -> new ValidationErrorResponse(fieldError.getField(),
                fieldError.getDefaultMessage()))
            .toList())
        .orElse(Collections.emptyList());

    return ErrorResponse.builder()
        .code(VALIDATION_ERROR.name())
        .message(VALIDATION_ERROR.getValue())
        .validationErrors(fieldErrors)
        .build();

  }

  @ExceptionHandler(MaxQuantityExceededException.class)
  public ErrorResponse handle(MaxQuantityExceededException e) {
    log.error("MaxQuantityExceededException: ", e);
    return ErrorResponse.builder()
        .code(e.getCode())
        .message(e.getMessage())
        .build();
  }

  @ExceptionHandler(OutOfStockException.class)
  public ErrorResponse handle(OutOfStockException e) {
    log.error("OutOfStockException: ", e);
    return ErrorResponse.builder()
        .code(e.getCode())
        .message(e.getMessage())
        .build();
  }

  @ExceptionHandler(ProductNotFoundException.class)
  public ErrorResponse handle(ProductNotFoundException e) {
    log.error("ProductNotFoundException: ", e);
    return ErrorResponse.builder()
        .code(e.getCode())
        .message(e.getMessage())
        .build();
  }

  @ExceptionHandler(ReservationNotFoundException.class)
  public ErrorResponse handle(ReservationNotFoundException e) {
    log.error("ReservationNotFoundException: ", e);
    return ErrorResponse.builder()
        .code(e.getCode())
        .message(e.getMessage())
        .build();
  }

}