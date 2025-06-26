package gift.repository;

import gift.entity.Product;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class ProductRepository {

  final Map<Long, Product> products = new HashMap<>();

  public Product get(long productId) {
    return products.get(productId);
  }

  public void add(Product product) {
    products.put(product.productId(), product);
  }

  public void delete(long productId) {
    products.remove(productId);
  }

  public Collection<Product> findAll() {
    return products.values();
  }

  public boolean containsKey(long id) {
    return products.containsKey(id);
  }
}