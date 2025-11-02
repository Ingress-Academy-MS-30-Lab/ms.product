package az.ingress.config;

import az.ingress.config.properties.RedisProperties;
import lombok.RequiredArgsConstructor;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RedissonConfig {
  private final RedisProperties redisProperties;

  @Bean
  public RedissonClient redissonClient() {
    var config = new Config();

    config
        .setCodec(new JsonJacksonCodec())
        .useSingleServer()
        .setAddress(redisProperties.getAddress())
        .setPassword(redisProperties.getPassword());

    return Redisson.create(config);
  }

}
