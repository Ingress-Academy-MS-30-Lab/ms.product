package az.ingress.model.response;

import java.time.LocalDateTime;
import java.util.List;
import az.ingress.model.enums.ReservationStatus;
import lombok.Data;

@Data
public class ReservationResponse {

  private String reservationId;
  private String reason;
  private ReservationStatus status;
  private LocalDateTime expiresAt;
  private List<ProductIssueResponse> issues;

}