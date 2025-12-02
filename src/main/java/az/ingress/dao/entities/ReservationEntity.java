package az.ingress.dao.entities;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.EnumType.STRING;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import az.ingress.model.enums.OrderStatus;
import az.ingress.model.enums.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "reservation")
@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "uuid")
@AllArgsConstructor
@NoArgsConstructor
public class ReservationEntity {

  @Id
  private String uuid;
  private Long orderId;
  private Long userId;
  private LocalDateTime reservedAt = LocalDateTime.now();
  private LocalDateTime expiresAt;
  @Enumerated(STRING)
  private ReservationStatus reservationStatus;
  @Enumerated(STRING)
  private OrderStatus orderStatus;
  @OneToMany(mappedBy = "reservation", cascade = ALL, orphanRemoval = true)
  private List<ProductReservationEntity> reservationProducts;

  @PrePersist
  public void generateUuid() {
    if (this.uuid == null) {
      this.uuid = UUID.randomUUID().toString();
    }
  }

}