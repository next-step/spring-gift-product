package repository;

import gift.entity.Product;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ProductRepositoryImpl implements ProductRepository {

    private final Map<Long, Product> products = new HashMap<>();

    private Long idNum = 1L;

    @Override
    public Product save(Product product) {
        if (product.getId() == null) {
            product.setId(idNum++);
        }
        products.put(product.getId(), product);
        return product;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(products.get(id));
    }

    @Override
    public void deleteById(Long id) {
        products.remove(id);
    }
}
