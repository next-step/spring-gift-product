package gift.repository;

import gift.dto.response.ProductGetResponseDto;
import gift.entity.Product;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    // 제거
    private final Map<Long, Product> products = new HashMap<>();

    private final JdbcTemplate jdbcTemplate;

    public ProductRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Product saveProduct(Product product) {

        String sql = "INSERT INTO products(name, price, image_url) VALUES(?,?,?)";
        jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getImageUrl());

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
