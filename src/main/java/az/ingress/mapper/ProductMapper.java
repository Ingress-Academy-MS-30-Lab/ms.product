package az.ingress.mapper;

import static java.util.Objects.isNull;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import az.ingress.dao.entities.ProductAttributeValueEntity;
import az.ingress.dao.entities.ProductEntity;
import az.ingress.dao.entities.ProductGroupEntity;
import az.ingress.dao.entities.ProductImageEntity;
import az.ingress.dao.repositories.projection.ProductVariantProjection;
import az.ingress.model.response.CartProductResponse;
import az.ingress.model.response.CartResponse;
import az.ingress.model.response.ProductDetailsResponse;
import az.ingress.model.response.VariantDimension;
import az.ingress.model.response.VariantOption;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(
    uses = {ProductAttributeValueMapper.class},
    nullValuePropertyMappingStrategy = IGNORE)
public interface ProductMapper {

  ProductMapper PRODUCT_MAPPER = Mappers.getMapper(ProductMapper.class);

  @Mapping(target = "productId", source = "product.id")
  @Mapping(target = "productGroupId", source = "product.productGroup.id")
  @Mapping(target = "supplier", source = "product.productGroup.supplier")
  @Mapping(target = "title", source = "product.productGroup.title")
  @Mapping(target = "description", source = "product.productGroup.description")
  @Mapping(target = "rating", source = "product.productGroup.rating")
  @Mapping(target = "basePrice", source = "product.productGroup.basePrice")
  @Mapping(target = "reviewCount", source = "product.productGroup.reviewCount")
  @Mapping(target = "category", source = "product.productGroup", qualifiedByName = "mapToCategoryResponse")
  @Mapping(target = "variantDimensions", source = "otherVariants", qualifiedByName = "mapToVariantDimensions")
  ProductDetailsResponse mapToProductDetailsResponse(ProductEntity product,
                                                     List<ProductImageEntity> images,
                                                     List<ProductAttributeValueEntity> selectedProductAttributes,
                                                     List<ProductVariantProjection> otherVariants,
                                                     @Context Long id);

  @Named("mapToCategoryResponse")
  default ProductDetailsResponse.CategoryResponse mapToCategoryResponse(
      ProductGroupEntity productGroup
  ) {
    if (productGroup == null) {
      return null;
    }
    return ProductDetailsResponse.CategoryResponse.builder()
        .id(productGroup.getCategoryId())
        .name(productGroup.getCategoryName())
        .pathName(productGroup.getCategoryPath())
        .build();
  }

  @Named("mapToVariantDimensions")
  default List<VariantDimension> mapToVariantDimensions(
      List<ProductVariantProjection> otherVariants,
      @Context Long selectedProductId) {

    if (isNull(otherVariants)) {
      return new ArrayList<>();
    }

    Map<Long, List<ProductVariantProjection>> variantsByAttribute = otherVariants.stream()
        .collect(Collectors.groupingBy(ProductVariantProjection::getAttributeId));

    return variantsByAttribute.entrySet().stream()
        .map(entry -> VariantDimension.builder()
            .attributeId(entry.getKey())
            .attributeName(entry.getValue().get(0).getAttributeName())
            .options(entry.getValue().stream()
                .map(variant -> VariantOption.builder()
                    .value(variant.getValue())
                    .productId(variant.getProductId())
                    .inStock(variant.getInStock())
                    .isSelected(variant.getProductId().equals(selectedProductId))
                    .build())
                .toList())
            .build())
        .toList();
  }

  // Map a single product to a variant response
  @Mapping(target = "productId", source = "id")
//  @Mapping(target = "imageUrl", source = "mainImage.url")
//  @Mapping(target = "price", source = "price")
  @Mapping(target = "stockQuantity", source = "stockQuantity")
  @Mapping(target = "inStock", source = "inStock")
//  @Mapping(target = "attributes", source = "attributeValues")
  CartProductResponse mapToVariantCartResponse(ProductEntity product);

  default List<CartResponse> mapToProductCartResponseList(List<ProductEntity> products) {
    Map<Long, List<ProductEntity>> grouped = products.stream()
        .collect(Collectors.groupingBy(p -> p.getProductGroup().getId()));

    return grouped.values().stream()
        .map(this::mapToProductCartResponse)
        .toList();
  }

  default CartResponse mapToProductCartResponse(List<ProductEntity> groupProducts) {
    ProductEntity product = groupProducts.get(0);

    var cartResponse = CartResponse.builder()
        .productGroupId(product.getProductGroup().getId())
        .supplierId(product.getProductGroup().getSupplier().getId())
        .supplierUsername(product.getProductGroup().getSupplier().getUsername())
        .categoryId(product.getProductGroup().getCategoryId())
        .categoryName(product.getProductGroup().getCategoryName())
        .title(product.getProductGroup().getTitle())
        .build();

    var variants = groupProducts.stream()
        .map(this::mapToVariantCartResponse)
        .toList();
    cartResponse.setProducts(variants);

    return cartResponse;
  }

//  PageResponse<ProductPreviewResponse> mapToPageProductDetailsResponse(Page<ProductEntity> page);

}
