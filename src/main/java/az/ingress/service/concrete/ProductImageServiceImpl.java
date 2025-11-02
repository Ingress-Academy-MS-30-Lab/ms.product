package az.ingress.service.concrete;


import java.util.List;
import az.ingress.dao.entities.ProductImageEntity;
import az.ingress.dao.repositories.ProductImageRepository;
import az.ingress.service.abstraction.ProductImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductImageServiceImpl implements ProductImageService {

  private final ProductImageRepository productImageRepository;

  @Override
  //cache
  public List<ProductImageEntity> getProductImagesByProductId(Long id) {
    return productImageRepository.findByProductId(id);
//    return PRODUCT_IMAGE_MAPPER.mapToImageResponseList(images);
  }

}
