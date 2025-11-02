package az.ingress.dao.repositories;

import az.ingress.dao.entities.ProductReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductReservationRepository extends JpaRepository<ProductReservationEntity, String> {

}
