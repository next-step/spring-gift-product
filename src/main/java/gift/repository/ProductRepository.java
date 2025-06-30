package gift.repository;

import gift.domain.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final AtomicLong sequence = new AtomicLong(0);

    private SimpleJdbcInsert insert;

    @PostConstruct
    public void init() {
        this.insert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("product")
                .usingGeneratedKeyColumns("id");
    }

    public Product save(String name, int price, String imageUrl) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("price", price);
        params.put("image_url", imageUrl);
        params.put("category_id", null);

        Number id = insert.executeAndReturnKey(new MapSqlParameterSource(params));
        return new Product(id.longValue(), name, price, imageUrl);
    }

    public Optional<Product> findById(Long id) {
        String sql = "SELECT * FROM product WHERE id = ?";
        List<Product> result = jdbcTemplate.query(sql, rowMapper(), id);
        return result.stream().findAny();
    }

    public List<Product> findAll() {
        return jdbcTemplate.query("SELECT * FROM product", rowMapper());
    }

    private RowMapper<Product> rowMapper() {
        return (rs, rowNum) -> new Product(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getInt("price"),
                rs.getString("image_url")
        );
    }

    public boolean deleteById(Long id) {
        int updated = jdbcTemplate.update("DELETE FROM product WHERE id = ?", id);
        return updated > 0;
    }

    public Optional<Product> update(Long id, String name, int price, String imageUrl) {
        String sql = "UPDATE product SET name = ?, price = ?, image_url = ? WHERE id = ?";
        int updated = jdbcTemplate.update(sql, name, price, imageUrl, id);
        if (updated == 0) return Optional.empty();
        return findById(id);
    }
}
