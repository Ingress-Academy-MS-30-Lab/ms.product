package az.ingress.service.concrete;

import static az.ingress.mapper.ProductAttributeValueMapper.PRODUCT_ATTRIBUTE_VALUE_MAPPER;

import java.util.List;
import az.ingress.dao.entities.ProductAttributeValueEntity;
import az.ingress.dao.repositories.ProductAttributeValueRepository;
import az.ingress.model.response.AttributeResponse;
import az.ingress.service.abstraction.ProductAttributeValueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductAttributeServiceImpl implements ProductAttributeValueService {

  private final ProductAttributeValueRepository productAttributeValueRepository;

  @Override
  public List<ProductAttributeValueEntity> getProductAttributesByProductId(Long productId) {
    return productAttributeValueRepository.findByProductId(productId);
//    return PRODUCT_ATTRIBUTE_VALUE_MAPPER.mapToAttributeResponseList(productAttributesValue);
  }

}
