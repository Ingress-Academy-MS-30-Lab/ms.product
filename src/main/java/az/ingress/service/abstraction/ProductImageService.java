package az.ingress.service.abstraction;

import java.util.List;
import az.ingress.dao.entities.ProductImageEntity;

public interface ProductImageService {

  List<ProductImageEntity> getProductImagesByProductId(Long id);

}
