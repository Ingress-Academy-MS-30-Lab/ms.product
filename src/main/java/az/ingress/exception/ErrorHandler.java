package az.ingress.exception;

import static az.ingress.exception.ErrorMessage.UNEXPECTED_ERROR;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

import az.ingress.model.response.ErrorResponse;
import az.ingress.model.response.ProductValidationErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {

  @ExceptionHandler(Exception.class)
  @ResponseStatus(INTERNAL_SERVER_ERROR)
  public ErrorResponse handle(Exception ex) {
    log.error("Exception: ", ex);
    return new ErrorResponse(UNEXPECTED_ERROR.name(),
        UNEXPECTED_ERROR.getValue());
  }

  @ExceptionHandler(ProductNotFoundException.class)
  @ResponseStatus(NOT_FOUND)
  public ErrorResponse handle(ProductNotFoundException e) {
    log.error("ProductNotFoundException : ", e);
    return new ErrorResponse(e.getErrorCode(), e.getMessage());
  }

  @ExceptionHandler(ProductValidationException.class)
  @ResponseStatus(UNPROCESSABLE_ENTITY)
  public ProductValidationErrorResponse handle(ProductValidationException e) {
    log.error("ProductValidationException: ", e);
    return ProductValidationErrorResponse.builder()
        .errorCode(e.getErrorCode())
        .message(e.getMessage())
        .productIssueResponses(e.getIssues())
        .build();

  }

}