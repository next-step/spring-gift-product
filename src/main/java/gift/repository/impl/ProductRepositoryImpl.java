package gift.repository.impl;

import gift.model.Product;
import gift.repository.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final Map<Long, Product> products = new HashMap<>();
    private Long nextId = 1L;

    @Override
    public Product save(Product product) {
        product.setId(nextId);
        products.put(nextId++, product);
        return product;
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(products.get(id));
    }

    @Override
    public Product update(Long id, Product updatedProduct) {
        Product existingProduct = products.get(id);
        if (existingProduct != null) {
            existingProduct.updateFrom(updatedProduct);
            return existingProduct;
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        products.remove(id);
    }
}
