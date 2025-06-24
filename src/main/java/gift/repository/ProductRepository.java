package gift.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import gift.domain.Product;

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
}
