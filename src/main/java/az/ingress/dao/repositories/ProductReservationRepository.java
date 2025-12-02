package az.ingress.dao.repositories;

import java.util.Optional;
import az.ingress.dao.entities.ReservationEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductReservationRepository extends JpaRepository<ReservationEntity, Long> {

  @EntityGraph(attributePaths = {"reservationProducts"})
  Optional<ReservationEntity> findByUuidAndOrderId(String reservationId, Long orderId);

}
