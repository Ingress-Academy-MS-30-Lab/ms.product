package az.ingress.model.response;

import lombok.Data;

@Data
public class ProductPreviewResponse {

  private Long productGroupId;
  private Long productId;
  private Long categoryId;
  private String categoryName;
  private String imageUrl;
  private String title;
  private String description;
  private PriceResponse price;
  private Double rating;
  private Long reviewCount;


}
