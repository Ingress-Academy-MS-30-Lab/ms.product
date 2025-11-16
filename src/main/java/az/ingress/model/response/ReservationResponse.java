package az.ingress.model.response;

import java.time.LocalDateTime;
import az.ingress.model.enums.ReservationStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReservationResponse {

  private String reservationId;
  private String reason;
  private ReservationStatus status;
  private LocalDateTime expiresAt;

}