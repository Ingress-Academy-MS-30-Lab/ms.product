package az.ingress.dao.repositories.projection;

import java.math.BigDecimal;

public interface ProductPreviewProjection {

  Long getProductId();

  Long getCategoryId();

  String getCategoryName();

  String getCategoryPath();

  String getBaseImageUrl();

  String getTitle();

  String getDescription();

  BigDecimal getPrice();

  BigDecimal getSalePrice();

  Double getRating();

  Long getReviewCount();

  Long getStockQuantity();

  Boolean getInStock();

  Long getSupplierId();

  String getSupplierUsername();

}
