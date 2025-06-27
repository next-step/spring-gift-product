package gift.product.repository;

import gift.common.dto.PagedResult;
import gift.product.domain.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {
  void save(Product product);
  Optional<Product> findById(Long id);
  PagedResult<Product> findAll(int page, int size, String sortField, boolean asc);
  void update(Long id, Product updateProduct);
  void deleteById(Long id);

}
