package az.ingress.mapper;

import java.util.List;
import az.ingress.dao.entities.ProductImageEntity;
import az.ingress.model.response.ImageResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductImageMapper {

  ProductImageMapper PRODUCT_IMAGE_MAPPER = Mappers.getMapper(ProductImageMapper.class);

  ImageResponse mapToImageResponse(ProductImageEntity imageEntity);

  List<ImageResponse> mapToImageResponseList(List<ProductImageEntity> imageEntities);

}
