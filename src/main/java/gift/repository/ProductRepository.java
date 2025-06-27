package gift.repository;

import gift.dto.api.ProductUpdateRequestDto;
import gift.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class ProductRepository {

    private final Map<Long, Product> storage = new HashMap<>();
    private long nextId = 1L;

    public Product save(Product product) {
        product.setId(nextId++);
        storage.put(product.getId(), product);
        return product;
    }

    public List<Product> findAll() {
        return new ArrayList<>(storage.values());
    }

    public Optional<Product> findById(long id) {
        return Optional.ofNullable(storage.get(id));
    }

    public void update(Long id, ProductUpdateRequestDto dto) {
        Product existing = storage.get(id);
        Product updated = new Product(
            dto.getName(),
            dto.getPrice(),
            dto.getImageUrl()
        );
        updated.setId(existing.getId());
        storage.put(id, updated);
    }

    public void delete(Long id) {
        storage.remove(id);
    }

}
