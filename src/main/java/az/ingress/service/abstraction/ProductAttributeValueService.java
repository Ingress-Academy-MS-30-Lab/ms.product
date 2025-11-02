package az.ingress.service.abstraction;

import java.util.List;
import az.ingress.dao.entities.ProductAttributeValueEntity;

public interface ProductAttributeValueService {

  List<ProductAttributeValueEntity> getProductAttributesByProductId(Long productId);


}
