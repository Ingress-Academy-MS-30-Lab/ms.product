package az.ingress.model.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ValidationMessages {

  public static final String EMPTY_ORDER_ID = "validation.emptyOrderId";
  public static final String EMPTY_USER_ID = "validation.emptyUserId";
  public static final String EMPTY_PRODUCT_ID = "validation.emptyProductId";
  public static final String EMPTY_PRODUCT_VARIANT_ID = "validation.emptyVariantId";
  public static final String EMPTY_QUANTITY = "validation.emptyQuantity";
  public static final String EMPTY_ORDER_STATUS = "validation.emptyOrderStatus";
  public static final String NULL_PRICE = "validation.nullPrice";
  public static final String INVALID_PRICE = "Price can't be negative";

}
