package az.ingress.model.request;

import static az.ingress.model.constant.ValidationMessages.EMPTY_USER_ID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import lombok.Data;

@Data
public class ReservationRequest {

  @NotNull(message = EMPTY_USER_ID)
  private Long userId;

  @NotBlank
  private List<ProductVariantReservationItemRequest> products;


}
