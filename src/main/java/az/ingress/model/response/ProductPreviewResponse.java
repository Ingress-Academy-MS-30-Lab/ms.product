package az.ingress.model.response;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ProductPreviewResponse {

  private Long productId;
  private Long categoryId;
  private String categoryName;
  private String categoryPath;
  private String baseImageUrl;
  private String title;
  private String description;
  private BigDecimal price;
  private BigDecimal salePrice;
  private Double rating;
  private Long reviewCount;
  private Long stockQuantity;
  private Boolean inStock;
  private Long supplierId;
  private String supplierUsername;

}