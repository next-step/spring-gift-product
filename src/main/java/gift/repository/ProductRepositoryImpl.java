package gift.repository;

import gift.dto.response.ProductGetResponseDto;
import gift.entity.Product;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

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
    }

    @Override
    public Product findProductByProductId(Long productId) {

        String sql = "SELECT productId, name, price, imageUrl FROM products WHERE productId = ?";
        Product product = jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
                new Product(
                    rs.getLong("productId"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getString("imageUrl")
                ),
            productId
        );

        return product;
    }

    @Override
    public int updateProductByProductId(Long productId, String name, Double price,
        String imageUrl) {

        String sql = "UPDATE products SET name = ?, price = ?, imageUrl = ? WHERE productId = ?";
        return jdbcTemplate.update(sql, name, price, imageUrl, productId);
    }


    @Override
    public int deleteProductByProductId(Long productId) {

        String sql = "DELETE FROM products WHERE productId = ?";
        return jdbcTemplate.update(sql, productId);
    }
}
