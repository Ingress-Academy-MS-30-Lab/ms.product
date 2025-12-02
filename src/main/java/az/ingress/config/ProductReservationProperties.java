package az.ingress.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@RefreshScope
@ConfigurationProperties("product.reservation")
public class ProductReservationProperties {

  private int maxQuantity;
  private int expiresAfterMinutes;

}
