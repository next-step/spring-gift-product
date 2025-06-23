package gift.repository;

import gift.domain.Product;
import gift.dto.CreateProductRequest;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class ProductMemoryRepository implements ProductRepository{

    private final Map<Long, Product> products = new HashMap<>();
    static Long sequence = 0L;

    @Override
    public Product save(CreateProductRequest request) {
        Long id = ++sequence;
        Product product = new Product(id, request.getName(), request.getPrice(), request.getImageUrl());
        products.put(id, product);

        return product;

    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(products.get(id));
    }
}
