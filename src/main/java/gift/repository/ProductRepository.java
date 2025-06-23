package gift.repository;

import gift.domain.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {
  Product findById(Long productId);
  void save(Product product);
  void deleteById(Long productId);

  List<Product> findAll();

}
