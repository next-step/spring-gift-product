package gift.repository;

import gift.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final Map<Long, Product> products = new HashMap<>();

    @Override
    public List<Product> findAllProducts() {

        List<Product> allProducts = new ArrayList<>();
        allProducts.addAll(products.values());
        return allProducts;
    }

    @Override
    public Product saveProduct(Product product) {
        Long productId = products.isEmpty() ? 1 : Collections.max(products.keySet()) + 1;
        product.setId(productId);
        products.put(productId, product);
        return product;
    }

    @Override
    public void deleteProduct(Long id) {
        products.remove(id);
    }

    @Override
    public Product findProduct(Long id) {
        return products.get(id);
    }
}
