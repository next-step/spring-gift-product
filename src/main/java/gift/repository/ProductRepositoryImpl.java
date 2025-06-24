package gift.repository;

import gift.dto.ProductResponseDto;
import gift.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private final Map<Long, Product> products = new HashMap<>();

    @Override
    public Product saveProduct(Product product) {
        Long productId = products.isEmpty() ? 1 : Collections.max(products.keySet()) + 1;
        product.setId(productId);
        products.put(productId, product);
        return product;
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        return Optional.ofNullable(products.get(id));
    }

    @Override
    public List<Product> findAllProducts() {
        return new ArrayList<>(products.values());
    }

    @Override
    public void deleteProductById(Long id) {
        products.remove(id);
    }
}
