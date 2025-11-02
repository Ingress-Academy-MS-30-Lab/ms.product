package az.ingress.model.response;

import java.math.BigDecimal;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDetailsResponse {

  private Long productId;
  private Long productGroupId;
  private SupplierDetails supplier;
  private Long categoryId;
  private String title;
  private String description;
  private Double rating;
  private Long reviewCount;
  private BigDecimal price;
  private BigDecimal basePrice;
  private BigDecimal salePrice;
  private Boolean onSale;
  private Long stockQuantity;
  private Boolean inStock;
  private CategoryResponse category;
  private List<ImageResponse> images;
  private List<AttributeResponse> selectedProductAttributes;
  private List<VariantDimension> variantDimensions;

  @Data
  @Builder
  public static class SupplierDetails {
    private Long id;
    private String username;
  }

  @Data
  @Builder
  public static class CategoryResponse {
    private Long id;
    private String name;
    private String pathName;
  }

}
