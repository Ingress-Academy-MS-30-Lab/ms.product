package az.ingress.mapper;

import az.ingress.dao.entities.ProductReservationEntity;
import az.ingress.dao.entities.ReservationEntity;
import az.ingress.model.request.ProductQuantityRequest;
import az.ingress.model.request.ReservationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductReservationMapper {

  ProductReservationMapper PRODUCT_RESERVATION_MAPPER = Mappers.getMapper(ProductReservationMapper.class);

  @Mapping(source = "products", target = "reservationProducts")
  ReservationEntity mapToReservation(ReservationRequest request);

  @Mapping(source = "productId",target = "product.id")
  ProductReservationEntity mapToProductReservation(ProductQuantityRequest productQuantityRequest);

}
