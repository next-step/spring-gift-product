package gift.repository;

import gift.entity.Product;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcProductRepository implements ProductRepository {

    private static final int NO_ROWS_AFFECTED = 0;

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Product> productRowMapper = (rs, rowNum) -> new Product(
            rs.getLong("id"),
            rs.getString("name"),
            rs.getInt("price"),
            rs.getString("image_url")
    );

    public JdbcProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Product> findAll() {
        String sql = "SELECT id, name, price, image_url FROM products";
        return jdbcTemplate.query(sql, productRowMapper);
    }

    @Override
    public Optional<Product> findById(Long id) {
        String sql = "SELECT id, name, price, image_url FROM products WHERE id = ?";
        try {
            Product product = jdbcTemplate.queryForObject(sql, productRowMapper, id);
            return Optional.ofNullable(product);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Product save(Product product) {
        String sql = "INSERT INTO products (name, price, image_url) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, product.getName());
            ps.setInt(2, product.getPrice());
            ps.setString(3, product.getImageUrl());
            return ps;
        }, keyHolder);

        Long generatedId = keyHolder.getKey().longValue();
        return product.assignId(generatedId);
    }

    @Override
    public Product update(Long id, String name, int price, String imageUrl) {
        String sql = "UPDATE products SET name = ?, price = ?, image_url = ? WHERE id = ?";
        var affectedRows = jdbcTemplate.update(sql, name, price, imageUrl, id);

        if (affectedRows == 0) {
            throw new NoSuchElementException("해당 ID의 상품이 존재하지 않아 업데이트할 수 없습니다: " + id);
        }
        return new Product(id, name, price, imageUrl);
    }

    @Override
    public boolean delete(Long id) {
        String sql = "DELETE FROM products WHERE id = ?";
        int affectedRows = jdbcTemplate.update(sql, id);
        return affectedRows > NO_ROWS_AFFECTED;
    }
}
