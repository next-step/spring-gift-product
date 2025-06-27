package gift.repository;

import gift.dto.response.ProductGetResponseDto;
import gift.entity.Product;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

        String sql = "INSERT INTO products(name, price, imageUrl) VALUES(?,?,?)";
        jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getImageUrl());

        return product;
    }

    @Override
    public List<ProductGetResponseDto> findAllProducts() {

        String sql = "SELECT productId, name, price, imageUrl FROM products";
        List<ProductGetResponseDto> productsList = jdbcTemplate.query(sql, (rs, rowNum) ->
            new ProductGetResponseDto(
                rs.getLong("productId"),
                rs.getString("name"),
                rs.getDouble("price"),
                rs.getString("imageUrl")
            ));

        return productsList;

//        return products.values().stream()
//            .map(product -> new ProductGetResponseDto(product))
//            .collect(Collectors.toList());
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
