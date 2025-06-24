package gift.repository;

import gift.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductRepository {

    private final Map<Long, Product> products = new HashMap<>();

    public Product save(Product product) {
        products.put(product.getId(), product);
        return product;
    }

    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    public Optional<Product> findById(Long id) {
        Product product = products.get(id);
        return Optional.ofNullable(product);
    }

    public void deleteByid(Long id) {
        if (!products.containsKey(id)) {
            throw new IllegalStateException("해당 id의 상품이 존재하지 않습니다.");
        }
        products.remove(id);
    }
}
