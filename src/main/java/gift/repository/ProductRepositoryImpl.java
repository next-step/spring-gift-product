package gift.repository;

import gift.dto.ProductResponseDto;
import gift.entity.Product;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private final Map<Long, Product> productList = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(0);

    @Override
    public List<Product> findAllProducts() {
        return new ArrayList<>(productList.values()); // 엔티티 그대로 반환
    }

    @Override
    public Product saveProduct(Product product) {
        long productId = idGenerator.incrementAndGet();
        product.setId(productId);
        productList.put(productId, product);
        return product;
    }

    @Override
    public Product findProductById(Long id) {
        return productList.get(id);
    }

    @Override
    public Product updateProduct(Long id, String name, Long price, String imageUrl) {
        Product product = productList.get(id);
        product.updateProduct(name, price, imageUrl);
        return product;
    }

    @Override
    public void deleteProduct(Long id) {
        productList.remove(id);
    }
}
