package az.ingress.dao.entities;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
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
@Table(name = "product_group")
public class ProductGroupEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  @Column(columnDefinition = "TEXT")
  private String description;
  private Double rating;
  private Long reviewCount;
  private Long categoryId;
  private String categoryName;
  @Column(columnDefinition = "TEXT")
  private String categoryPath;
  private BigDecimal basePrice;
  private Boolean isSponsored;
  private Boolean isActive;
  @OneToMany(
      cascade = CascadeType.ALL,
      mappedBy = "productGroup"
  )
  private List<ProductEntity> products;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "product_group_id", referencedColumnName = "id")
  private List<ProductReservationEntity> productReservations;

  private LocalDateTime updatedAt;

  @Embedded
  @AttributeOverride(name = "id", column = @Column(name = "created_by"))
  @AttributeOverride(name = "username", column = @Column(name = "created_by_username"))
  @AttributeOverride(name = "timestamp", column = @Column(name = "created_at"))
  private AuditInfo supplier;

  @Embedded
  @AttributeOverride(name = "id", column = @Column(name = "approved_by"))
  @AttributeOverride(name = "username", column = @Column(name = "approved_by_username"))
  @AttributeOverride(name = "timestamp", column = @Column(name = "approved_at"))
  private AuditInfo approvedBy;


  @Embeddable
  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class AuditInfo {

    private Long id;
    private String username;
    private LocalDateTime timestamp;

  }

}
