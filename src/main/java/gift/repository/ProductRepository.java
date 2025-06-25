package gift.repository;

import gift.dto.ProductUpdateRequestDto;
import gift.entity.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
        if (existing != null) {
            existing.setName(dto.getName());
            existing.setPrice(dto.getPrice());
            existing.setImageUrl(dto.getImageUrl());
        }
    }

    public void delete(Long id) {
        storage.remove(id);
    }

}
