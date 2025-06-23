package gift.repository;

import gift.domain.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl implements ProductRepository{

  private final Map<Long, Product> products = new HashMap<>();

  @Override
  // Optional 객체를 return하지 않고 그냥 여기서 예외를 터트리도록 처리했습니다.
  public Product findById(Long productId) {
    Product product = products.get(productId);

    if(product==null)
        throw new NoSuchElementException("찾으시는 상품이 없습니다.");

    return product;
  }

  @Override
  public void save(Product product) {
    products.put(product.getId(), product);
  }

  @Override
  public void deleteById(Long productId) {
    products.remove(productId);
  }

  @Override
  public List<Product> findAll() {
    return products.values().stream().toList();
  }
}
