package gift.repository;

import gift.dto.ProductResponseDto;
import gift.entity.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

    private final Map<Long, Product> products = new ConcurrentHashMap<>();

    private final AtomicLong idCounter = new AtomicLong(1);

    public Product saveProduct(Product product) {
        product.setId(idCounter.getAndIncrement());
        products.put(product.getId(), product);
        return product;
    }

    public List<ProductResponseDto> findAllProducts() {
        List<ProductResponseDto> allProducts = new ArrayList<>();
        for (Product product : products.values()) {
            ProductResponseDto responseDto = new ProductResponseDto(product);
            allProducts.add(responseDto);
        }

        return allProducts;
    }

    public Product findProductById(Long id) {
        return products.get(id);
    }

    public void deleteProduct(Long id) {
        products.remove(id);
    }
}
