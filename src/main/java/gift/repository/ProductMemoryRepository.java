package gift.repository;

import gift.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductMemoryRepository implements ProductRepository {

    private final Map<Long, Product> store = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public Product save(Product product) {
        Long id = idGenerator.getAndIncrement();
        Product savedProduct = new Product(id, product.getName(), product.getPrice(), product.getImageUrl());
        store.put(id, savedProduct);
        return savedProduct;
    }


    @Override
    public List<Product> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public void deleteById(Long id) {
        store.remove(id);
    }

    @Override
    public boolean existsById(Long id) {
        return store.containsKey(id);
    }
}

