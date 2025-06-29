package gift.product.repository;

import gift.global.common.dto.SortInfo;
import gift.product.domain.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {
  Long save(Product product);
  Optional<Product> findById(Long id);
  List<Product> findAll(int offset, int pageSize, SortInfo sortInfo);
  void update(Long id, Product updateProduct);
  void deleteById(Long id);

}
