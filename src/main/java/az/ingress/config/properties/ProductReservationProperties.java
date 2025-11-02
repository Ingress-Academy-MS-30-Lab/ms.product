package az.ingress.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Data
@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "product.reservation")
public class ProductReservationProperties {

  private Long maxQuantity;
  private Long expiresAfterMinutes;

}
