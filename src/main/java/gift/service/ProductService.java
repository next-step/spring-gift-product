package gift;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final Map<Long, Product> products = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Product createProduct(ProductRequest request) {
        if (request.name() == null || request.price() == null || request.imageUrl() == null) {
            throw new IllegalArgumentException("Required fields are missing");
        }
        Long id = request.id();
        if (id == null) {
            id = idGenerator.getAndIncrement();
        }
        if (products.containsKey(id)) {
            throw new IllegalArgumentException("ID(" + id + ") already exists");
        }
        Product product = new Product(id, request.name(), request.price(), request.imageUrl());
        products.put(id, product);
        return product;
    }

    public Product getProduct(Long productId) {
        Product product = products.get(productId);
        if (product == null) {
            throw new IllegalArgumentException("Product(id: " + productId + ") not found");
        }
        return product;
    }

    public Product updateProduct(Long productId, ProductRequest request) {
        Product existingProduct = products.get(productId);
        if (existingProduct == null) {
            throw new IllegalArgumentException("Product(id: " + productId + ") not found");
        }

        // 요청된 필드만 수정, 나머지 필드는 기존 값 유지
        String updatedName = existingProduct.getName();
        if (request.name() != null) {
            updatedName = request.name();
        }

        int updatedPrice = existingProduct.getPrice();
        if (request.price() != null) {
            updatedPrice = request.price();
        }

        String updatedImageUrl = existingProduct.getImageUrl();
        if (request.imageUrl() != null) {
            updatedImageUrl = request.imageUrl();
        }

        Product updatedProduct = new Product(productId, updatedName, updatedPrice, updatedImageUrl);
        products.put(productId, updatedProduct);
        return updatedProduct;
    }

    public void deleteProduct(Long productId) {
        if (!products.containsKey(productId)) {
            throw new IllegalArgumentException("Product(id: " + productId + ") not found");
        }
        products.remove(productId);
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }
}
