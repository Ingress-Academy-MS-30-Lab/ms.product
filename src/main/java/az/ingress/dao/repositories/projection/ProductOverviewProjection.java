package az.ingress.dao.repositories.projection;

public interface ProductOverviewProjection {

  Long getProductGroupId();
  Long getProductId();
  String getTitle();
  String getDescription();
  Double getRating();
  Long getReviewCount();
  Long getStockQuantity();
  Boolean getInStock();
  Long getCategoryId();
  String getCategoryName();
  String getCategoryPath();
  Long getSupplierId();
  Long getSupplierUsername();

}
