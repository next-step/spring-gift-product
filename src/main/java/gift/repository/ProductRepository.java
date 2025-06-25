package gift.repository;

import gift.model.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

  private final Map<Long, Product> products = new HashMap<>();
  private Long id = 0L;

  public List<Product> findAll() {
    return new ArrayList<>(products.values());
  }

  public Optional<Product> findById(Long id) {
    return Optional.ofNullable(products.get(id));
  }

  public Product save(Product product) {
    product.setId(id++);
    products.put(product.getId(), product);
    return product;
  }

  public Product update(Long id, Product updateProduct) {
    updateProduct.setId(id);
    products.put(id, updateProduct);
    return updateProduct;
  }

  public void delete(Long id) {
    products.remove(id);
  }

  public boolean exists(Long id) {
    return products.containsKey(id);
  }
}
