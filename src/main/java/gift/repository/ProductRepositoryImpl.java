package gift.repository;

import gift.domain.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final Map<Long, Product> products = new ConcurrentHashMap<>();
    private final AtomicLong currentProductId = new AtomicLong(1L);

    @Override
    public List<Product> findAllProducts() {
        return new ArrayList<>(products.values());
    }

    @Override
    public Product findProductById(Long id) throws IllegalArgumentException {
        Product product = products.get(id);

        if (product == null) {
            throw new IllegalArgumentException("해당 ID의 상품이 존재하지 않습니다: " + id);
        }

        return product;
    }

    @Override
    public Product createProduct(Product product) {
        Product newProduct = new Product(
                currentProductId.getAndIncrement(),
                product.getName(),
                product.getPrice(),
                product.getImageUrl());

        products.put(newProduct.getId(), newProduct);

        return newProduct;
    }

    @Override
    public Product updateProduct(Product product)
            throws IllegalArgumentException {
        Product newProduct = products.get(product.getId());

        if (newProduct == null) {
            throw new IllegalArgumentException("해당 ID의 상품이 존재하지 않습니다: " + product.getId());
        }

        newProduct.setName(product.getName());
        newProduct.setPrice(product.getPrice());
        newProduct.setImageUrl(product.getImageUrl());

        return newProduct;
    }

    @Override
    public void deleteProduct(Long id) throws IllegalArgumentException {
        if (!products.containsKey(id)) {
            throw new IllegalArgumentException("해당 ID의 상품이 존재하지 않습니다: " + id);
        }

        products.remove(id);
    }
}
