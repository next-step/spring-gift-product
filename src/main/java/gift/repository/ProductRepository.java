package gift.repository;
import gift.entity.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Product findProductById(long id) {
        return jdbcTemplate.queryForObject("SELECT id, name, price, imageUrl FROM products WHERE id = ?", productRowMapper(), id);
    }

    public List<Product> findAllProducts() {
        return jdbcTemplate.query("SELECT id, name, price, imageUrl FROM products", productRowMapper());
    }

    public Product saveProduct(Product product) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("products").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", product.name());
        parameters.put("price", product.price());
        parameters.put("imageUrl", product.imageUrl());

        // 저장 후 자동생성된 키 저장
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        return new Product(key.longValue(), product.name(), product.price(), product.imageUrl());
    }

    public boolean updateProduct(Long id, Product product) {
        return jdbcTemplate.update("UPDATE products SET name = ?, price = ?, imageUrl = ? WHERE id = ?", product.name(), product.price(), product.imageUrl(), id) != 0;
    }

    public boolean deleteProduct(Long id) {
        return jdbcTemplate.update("DELETE FROM products WHERE id = ?", id) != 0;
    }


    // Product 전체 조회용 RowMapper
    private RowMapper<Product> productRowMapper() {
        return (rs, rowNum) -> new Product(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getLong("price"),
                rs.getString("imageUrl")
        );
    }
}