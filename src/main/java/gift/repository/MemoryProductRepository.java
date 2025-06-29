package gift.repository;

import gift.entity.Product;
import gift.exception.NotFoundByIdException;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class MemoryProductRepository implements ProductRepository {
    private final Map<Long, Product> products = new HashMap<>();

    @Override
    public Long saveProduct(String name, Integer price, String imageUrl) {
        Long id = (long) (products.size() + 1);
        Product product = new Product(id, name, price, imageUrl);
        products.put(id, product);
        return id;
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        if (!products.containsKey(id)) {
            throw new NotFoundByIdException("Product with id " + id + " does not exist.");
        }
        return Optional.of(products.get(id));
    }

    @Override
    public void deleteProductById(Long id) {
        products.remove(id);
    }

    @Override
    public void updateProduct(Product product) {
        if (!products.containsKey(product.id()))
            throw new NotFoundByIdException("Product with id " + product.id() + " does not exist.");
        products.put(product.id(), product);
    }

    @Override
    public List<Product> findAllProducts() {
        return products.values().stream().toList();
    }
}
