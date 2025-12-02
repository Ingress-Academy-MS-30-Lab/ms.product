package az.ingress.dao.repositories;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;
import az.ingress.dao.entities.ProductEntity;
import az.ingress.dao.repositories.projection.ProductPreviewProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

  @Lock(LockModeType.PESSIMISTIC_WRITE)
  @QueryHints(
      @QueryHint(name = "javax.persistence.lock.timeout", value = "3000")
  )
  @Query("SELECT p FROM ProductEntity p WHERE p.id IN :ids")
  List<ProductEntity> findByIdIn(Iterable<Long> ids);


  @Query("""
          SELECT
              p.id AS productId,
              p.categoryId AS categoryId,
              p.categoryName AS categoryName,
              p.categoryPath AS categoryPath,
              pi.imageUrl AS baseImageUrl,
              p.title AS title,
              p.description AS description,
              p.price AS price,
              p.salePrice AS salePrice,
              p.rating AS rating,
              p.reviewCount AS reviewCount,
              p.stockQuantity AS stockQuantity,
              p.inStock AS inStock,
              p.createdById AS supplierId,
              p.createdByUsername AS supplierUsername
          FROM ProductEntity p
          LEFT JOIN ProductImageEntity pi
              ON pi.product = p AND pi.isPrimary = true
          WHERE p.id in :ids
      """)
  Page<ProductPreviewProjection> findByIdsIn(List<Long> ids, Pageable pageable);


}
