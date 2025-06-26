package gift.repository;

import gift.entity.Product;
<<<<<<< step2
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
=======
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
>>>>>>> hhseo9519

@Repository
public class ProductRepository {

<<<<<<< step2
    private final Map<Long, Product> store = new HashMap<>();
    private Long nextId = 1L;

    public List<Product> findAll() {
        return new ArrayList<>(store.values());
    }

    public Product save(Product product) {
        product.setId(nextId++);
        store.put(product.getId(), product);
        return product;
    }

    public void update(Long id, Product updatedProduct) {
        Product existing = store.get(id);
        if (existing != null) {
            existing.setName(updatedProduct.getName());
            existing.setPrice(updatedProduct.getPrice());
            existing.setImageUrl(updatedProduct.getImageUrl());
        }
    }

    public Product findById(Long id) {
        return store.get(id);
    }

    public void deleteById(Long id) {
        store.remove(id);
    }


=======
    private final Map<Long, Product> productMap = new HashMap<>();
    private Long nextId = 1L;

    public Product addProduct(Product product) {
        product.setId(nextId++);
        productMap.put(product.getId(), product);
        return product;
    }

    public Product findProduct(Long id) {
        return productMap.get(id);
    }

    public Product updateProduct(Product product) {
        productMap.put(product.getId(), product);
        return product;
    }

    public void deleteProduct(Long id) {
        productMap.remove(id);
    }
>>>>>>> hhseo9519
}
