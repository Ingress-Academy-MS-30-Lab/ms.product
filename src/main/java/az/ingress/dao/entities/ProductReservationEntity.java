package az.ingress.dao.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import az.ingress.model.enums.OrderStatus;
import az.ingress.model.enums.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "product_reservation")
@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "uuid")
@AllArgsConstructor
@NoArgsConstructor
public class ProductReservationEntity {

  @Id
  private String uuid;
  private Long orderId;
  private Long userId;
  private Long reservedQuantity;
  private LocalDateTime reservedAt = LocalDateTime.now();
  private LocalDateTime expiresAt;
  @Enumerated(EnumType.STRING)
  private ReservationStatus reservationStatus;
  @Enumerated(EnumType.STRING)
  private OrderStatus orderStatus;

  @PrePersist
  public void generateUuid() {
    if (this.uuid == null) {
      this.uuid = UUID.randomUUID().toString();
    }
  }

}
