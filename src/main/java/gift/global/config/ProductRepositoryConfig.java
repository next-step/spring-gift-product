package gift.global.config;

import gift.product.repository.InMemoryProductRepository;
import gift.product.repository.JdbcProductRepository;
import gift.product.repository.ProductRepository;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ProductRepositoryConfig {

  @Bean
  @Profile("dev")
  public ProductRepository jdbcProductRepository(DataSource dataSource) {
    return new JdbcProductRepository(dataSource);
  }
  @Bean
  @Profile("default")
  public ProductRepository inMemoryProductRepository() {
    return new InMemoryProductRepository();
  }
}

