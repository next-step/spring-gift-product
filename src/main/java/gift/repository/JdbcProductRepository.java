package gift.repository;

import gift.entity.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository("jdbcProductRepository")
public class JdbcProductRepository implements ProductRepositoryInterface{

    private JdbcTemplate jdbcTemplate;

    public JdbcProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long getNewProductId() {
        return 0;
    }

    @Override
    public Product addProduct(Product product) {
        String sql = "INSERT INTO product (name, price, imageUrl) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getImageUrl());

        return product;
    }

    private Product RowMapperProduct(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getInt("price"),
                rs.getString("imageUrl")
        );
        return product;
    }

    @Override
    public List<Product> findAllProducts() {
        String sql = "SELECT * FROM product";
        return jdbcTemplate.query(sql, this::RowMapperProduct);
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        String sql = "SELECT * FROM product WHERE id = ?";

        try {
            Product product = jdbcTemplate.queryForObject(sql, this::RowMapperProduct, id);
            return Optional.of(product);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Product> updateProduct(Long id, Product product) {

        String sql = "SELECT * FROM product WHERE id = ?";

        try {
            jdbcTemplate.queryForObject(sql, this::RowMapperProduct, id);
            sql = "UPDATE product SET name = ?, price = ?, imageUrl = ? WHERE id = ?";
            jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getImageUrl(), id);
            return Optional.of(new Product(id, product.getName(), product.getPrice(), product.getImageUrl()));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean deleteProduct(Long id) {
        String sql = "DELETE FROM product WHERE id = ?";
        int deleted = jdbcTemplate.update(sql, id);
        return deleted > 0;
    }
}
