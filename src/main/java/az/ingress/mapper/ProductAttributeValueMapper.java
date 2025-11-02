package az.ingress.mapper;

import java.util.List;
import az.ingress.dao.entities.ProductAttributeValueEntity;
import az.ingress.model.response.AttributeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductAttributeValueMapper {

  ProductAttributeValueMapper PRODUCT_ATTRIBUTE_VALUE_MAPPER =
      Mappers.getMapper(ProductAttributeValueMapper.class);

  @Mapping(target = "id", source = "attribute.id")
  @Mapping(target = "name", source = "attribute.name")
  @Mapping(target = "value", source = "value")
  @Mapping(target = "type", source = "attribute.type")
  AttributeResponse mapToAttributeResponse(ProductAttributeValueEntity entity);

  List<AttributeResponse> mapToAttributeResponseList(
      List<ProductAttributeValueEntity> productAttributeValueEntities
  );

}
