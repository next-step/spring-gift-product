package gift.repository;

import gift.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductMemoryRepository implements ProductRepository {

    private final Map<Long, Product> store = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    //등록
    @Override
    public Product save(Product product) {
        Long id = idGenerator.getAndIncrement();
        product.setId(id);
        store.put(id, product);
        return product;
    }

    //전체 조회
    @Override
    public List<Product> findAll() {
        return new ArrayList<>(store.values());
    }

    //단일 조회
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

