package az.ingress.model.request;

import static az.ingress.model.constant.ApplicationConstants.MIN_PRICE;
import static az.ingress.model.constant.ValidationMessages.EMPTY_ORDER_ID;
import static az.ingress.model.constant.ValidationMessages.EMPTY_ORDER_STATUS;
import static az.ingress.model.constant.ValidationMessages.EMPTY_RESERVATION_ID;
import static az.ingress.model.constant.ValidationMessages.EMPTY_USER_ID;
import static az.ingress.model.constant.ValidationMessages.INVALID_PRICE;
import static az.ingress.model.constant.ValidationMessages.NULL_PRICE;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import az.ingress.model.enums.OrderStatus;
import lombok.Data;

@Data
public class StockUpdateCallbackRequest {

  @NotNull(message = EMPTY_ORDER_ID)
  private Long orderId;

  @NotNull(message = EMPTY_RESERVATION_ID)
  private String reservationId;

  @NotNull(message = EMPTY_USER_ID)
  private Long userId;

  @NotNull(message = EMPTY_ORDER_STATUS)
  private OrderStatus status;

  @NotNull(message = NULL_PRICE)
  @DecimalMin(value = MIN_PRICE, message = INVALID_PRICE)
  private BigDecimal totalAmount;

  @NotEmpty
  private List<ProductQuantityRequest> items;

}