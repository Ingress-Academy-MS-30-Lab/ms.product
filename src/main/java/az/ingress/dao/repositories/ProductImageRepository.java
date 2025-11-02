package az.ingress.dao.repositories;

import java.util.List;
import az.ingress.dao.entities.ProductImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductImageRepository extends JpaRepository<ProductImageEntity, Long> {

  List<ProductImageEntity> findByProductId(Long productId);

  @Query("""
      SELECT pi
      FROM ProductImageEntity pi
      WHERE pi.isPrimary=true
            AND pi.product.id IN :productIds
      """)
  ProductImageEntity findPrimaryImageByProductId(List<Long> productIds);

}
