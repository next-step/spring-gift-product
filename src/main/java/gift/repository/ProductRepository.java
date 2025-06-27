package gift.repository;

import gift.domain.Product;
import org.springframework.dao.EmptyResultDataAccessException;
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

    public Product save(Product product) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("product").usingGeneratedKeyColumns("id");

        Map<String, Object> params = new HashMap<>();
        params.put("name", product.getName());
        params.put("price", product.getPrice());
        params.put("quantity", product.getQuantity());

        Long key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(params)).longValue();

        return new Product(key, product.getName(), product.getPrice(), product.getQuantity());
    }

    public List<Product> findAll() {
        String sql = "select * from product";
        return jdbcTemplate.query(sql, toProduct());
    }

    private RowMapper<Product> toProduct() {
        return (rs, rowNum) -> new Product(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getInt("price"),
                rs.getInt("quantity")
        );
    }

    public Optional<Product> findById(Long id) {
        String sql = "select * from product where id=?";
        try {
            Product product = jdbcTemplate.queryForObject(sql, toProduct(), id);
            return Optional.of(product);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public void deleteByid(Long id) {
        String sql = "delete from product where id=?";
        try {
            jdbcTemplate.update(sql, id);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalStateException(e);
        }

    }

    public void update(Product product) {
        String sql = "update product set name=?, price=?, quantity=? where id=?";
        jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getQuantity(), product.getId());
    }
}
