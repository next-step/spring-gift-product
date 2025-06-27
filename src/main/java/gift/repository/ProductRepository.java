package gift.repository;

import gift.dto.api.ProductUpdateRequestDto;
import gift.entity.Product;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Product save(Product product) {
        String sql = "INSERT INTO products (name, price, image_url) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getImageUrl());
        return product;
    }

    private Product mapRowToProduct(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product(
            rs.getString("name"),
            rs.getInt("price"),
            rs.getString("image_url")
        );
        product.setId(rs.getInt("id"));
        return product;
    }

    public List<Product> findAll() {
        String sql = "SELECT * FROM products";
        return jdbcTemplate.query(sql, this::mapRowToProduct);
    }

    public Optional<Product> findById(Long id) {
        String sql = "SELECT * FROM products WHERE id = ?";
        List<Product> result = jdbcTemplate.query(sql, this::mapRowToProduct, id);
        return result.stream().findFirst();
    }

    public void update(Long id, ProductUpdateRequestDto productUpdateRequestDto) {
        String sql = "UPDATE products SET name = ?, price = ?, image_url = ? WHERE id = ?";
        jdbcTemplate.update(sql,
            productUpdateRequestDto.getName(),
            productUpdateRequestDto.getPrice(),
            productUpdateRequestDto.getImageUrl(),
            id
        );
    }

    public void delete(Long id) {
        String sql = "DELETE FROM products WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

}
