package az.ingress.util;

import az.ingress.model.response.ProductDetailsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductCache {

  private final CacheUtil cacheUtil;
  private String PRODUCT_CACHE_NAME = "productDetails:";

  public ProductDetailsResponse getFromCache(Long id) {
    return cacheUtil.getBucket(PRODUCT_CACHE_NAME+id);
  }

}
