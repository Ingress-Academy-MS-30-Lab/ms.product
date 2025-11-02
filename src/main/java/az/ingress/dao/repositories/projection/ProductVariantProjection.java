package az.ingress.dao.repositories.projection;

public interface ProductVariantProjection {
  Long getProductId();
  Long getAttributeId();
  String getAttributeName();
  Boolean getInStock();
  String getValue();
}
