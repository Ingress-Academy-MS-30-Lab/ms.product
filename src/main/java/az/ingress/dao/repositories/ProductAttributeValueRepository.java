package az.ingress.dao.repositories;

import java.util.List;
import az.ingress.dao.entities.ProductAttributeValueEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductAttributeValueRepository extends JpaRepository<ProductAttributeValueEntity, Long> {

  @EntityGraph(attributePaths = "attribute")
  List<ProductAttributeValueEntity> findByProductId(Long productId);

}
