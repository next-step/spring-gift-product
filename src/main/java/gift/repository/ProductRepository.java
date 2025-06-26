package gift.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import gift.domain.Product;
import gift.exception.ProductNotFoundException;

@Repository
public class ProductRepository {

    private final Map<Long, Product> products = new HashMap<>();
    private static Long id = 1L;

    public List<Product> findAll() {
        return products.values()
            .stream()
            .toList();
    }

    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(products.get(id));
    }

    public Product save(String name, Integer price, String imageUrl) {
        products.put(id, new Product(id, name, price, imageUrl));
        return products.get(id++);
    }

    public Product update(Long id, String name, Integer price, String imageUrl) {
        if (!products.containsKey(id)) {
            throw new ProductNotFoundException("해당 상품이 존재하지 않습니다.");
        }

        products.put(id, new Product(id, name, price, imageUrl));
        return products.get(id);
    }

    public void delete(Long id) {
        if (!products.containsKey(id)) {
            throw new ProductNotFoundException("해당 상품이 존재하지 않습니다.");
        }

        products.remove(id);
    }
}
