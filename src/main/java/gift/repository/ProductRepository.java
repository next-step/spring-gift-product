package gift.repository;

import gift.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductRepository {

    private static final Map<Long, Product> store = new HashMap<>();
    private static final AtomicLong sequence = new AtomicLong(0L);

    public Product save(Product product) {
        product.setId(sequence.incrementAndGet());
        store.put(product.getId(), product);
        return product;
    }

    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public void update(Long id, Product updateParam) {
        Product findProduct = findById(id).get();
        findProduct.setName(updateParam.getName());
        findProduct.setPrice(updateParam.getPrice());
        findProduct.setImageURL(updateParam.getImageURL());
    }

    public List<Product> findAll() {
        return new ArrayList<>(store.values());
    }

}