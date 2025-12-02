package az.ingress.dao.entities;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "product")
public class ProductEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long stockQuantity;
  private Long reservedQuantity;
  private Long availableQuantity;
  private BigDecimal price;
  private BigDecimal salePrice;
  private Boolean onSale;
  private Boolean inStock;
  private Boolean isActive;
  private String title;
  @Column(columnDefinition = "TEXT")
  private String description;
  private Double rating;
  private Long reviewCount;
  private Long categoryId;
  private String categoryName;
  @Column(columnDefinition = "TEXT")
  private String categoryPath;
  private Boolean isSponsored;
  private Long createdById;
  private String createdByUsername;
  private LocalDateTime createdAt;
  private Long approvedById;
  private String approvedByUsername;
  private LocalDateTime approvedAt;
  @ElementCollection
  @CollectionTable(
      name = "product_attributes",
      joinColumns = @JoinColumn(name = "product_id")
  )
  @MapKeyColumn(name = "attribute_key")
  @Column(name = "attribute_value")
  private Map<String, String> attributes;
  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ProductImageEntity> images;
  @OneToMany(mappedBy = "product")
  private List<ProductReservationEntity> productReservationEntities;


}
