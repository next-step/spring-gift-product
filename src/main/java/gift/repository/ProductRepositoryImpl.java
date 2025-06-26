package gift.repository;

import gift.dto.response.ProductGetResponseDto;
import gift.entity.Product;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final Map<Long, Product> products = new HashMap<>();

    @Override
    public Product saveProduct(Product product) {

        long productId = products.isEmpty() ? 1 : Collections.max(products.keySet()) + 1;

        product.setProductId(productId);

        products.put(productId, product);

        return product;
    }

    @Override
    public List<ProductGetResponseDto> findAllProducts() {
        return products.values().stream()
            .map(product -> new ProductGetResponseDto(product))
            .collect(Collectors.toList());
    }

    @Override
    public Product findProductByProductId(Long productId) {
        return products.get(productId);
    }

    @Override
    public Product deleteProductByProductId(Long productId) {
        return products.remove(productId);
    }
}
