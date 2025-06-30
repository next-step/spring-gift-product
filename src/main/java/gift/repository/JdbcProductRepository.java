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
        return null;
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
        String sql = "SELECT * FROM products";
        return jdbcTemplate.query(sql, this::RowMapperProduct);
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Product> updateProduct(Long id, Product product) {
        return Optional.empty();
    }

    @Override
    public boolean deleteProduct(Long id) {
        return false;
    }
}
