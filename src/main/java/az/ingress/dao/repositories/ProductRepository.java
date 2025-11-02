package az.ingress.dao.repositories;

import java.util.List;
import java.util.Optional;
import az.ingress.dao.entities.ProductEntity;
import az.ingress.dao.repositories.projection.ProductVariantProjection;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

  @Query("""
      SELECT p
      FROM ProductEntity p
      JOIN ProductGroupEntity pg on pg.id = p.productGroup.id
      WHERE pg.categoryPath LIKE CONCAT('%/', :categoryId, '/%')
      """)
  Page<ProductEntity> findAllByCategoryId(Long categoryId, Pageable pageable);


  @EntityGraph(attributePaths = {"productGroup", "productGroup.supplier"})
  @Query("SELECT p FROM ProductEntity p WHERE p.isActive = true and p.id = :id")
  Optional<ProductEntity> findBaseInfoById(@NonNull Long id);

  @EntityGraph(attributePaths = {"productGroup", "productGroup.supplier"})
  @Query("""
      SELECT p, pi
      FROM ProductEntity p
      JOIN p.productImages pi
      WHERE p.isActive = true AND p.id = :id AND pi.isPrimary = true
      """)
  Optional<ProductEntity> findBaseInfoWithPrimaryImageById(Long id);

  @Query("""
      SELECT p.id as productId,
             a.id as attributeId,
             a.name as attributeName,
             p.inStock as inStock,
             pav.value as value
        FROM ProductEntity p
        JOIN p.productAttributeValues pav
        JOIN pav.attribute a
       WHERE a.id IN :attributeIds
      """)
  List<ProductVariantProjection> findProductVariantsByAttribute(List<Long> attributeIds);

  @EntityGraph(attributePaths = {"productGroup", "productImages", "productAttributeValues.attribute"})
  @Query("SELECT p FROM ProductEntity p WHERE p.id = :id AND p.isActive = true")
  Optional<ProductEntity> findProductDetailsById(Long id);

  @EntityGraph(attributePaths = {"productGroup"})
  @Query("""
      SELECT p FROM ProductEntity p WHERE p.id IN :ids
      """)
  List<ProductEntity> findAllByIds(List<Long> ids);

}
