package az.ingress.mapper;

import az.ingress.dao.repositories.projection.ProductPreviewProjection;
import az.ingress.model.response.PageResponse;
import az.ingress.model.response.ProductPreviewResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper
public interface ProductMapper {

  ProductMapper PRODUCT_MAPPER = Mappers.getMapper(ProductMapper.class);

  ProductPreviewResponse mapToProductPreview(ProductPreviewProjection productPreviewProjection);

  PageResponse<ProductPreviewResponse> mapToProductPreviewPageResponse(Page<ProductPreviewProjection> productEntity);

}
