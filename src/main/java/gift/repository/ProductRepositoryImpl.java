package gift.repository;

import gift.dto.ProductRequestDto;
import gift.entity.Product;
import gift.exception.FailedGenerateKeyException;
import gift.exception.ProductNotFoundException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private final JdbcTemplate jdbcTemplate;

    public ProductRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long createProduct(ProductRequestDto requestDto) {
        final String sql = "INSERT INTO products (name, price, image_url) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, requestDto.name());
            ps.setInt(2, requestDto.price());
            ps.setString(3, requestDto.imageUrl());
            return ps;
        }, keyHolder);
        Number key = keyHolder.getKey();
        if (key == null) {
            throw new FailedGenerateKeyException();
        }
        return key.longValue();
    }

    @Override
    public List<Product> findAllProducts() {
        final String sql = "SELECT * FROM products";

        return jdbcTemplate.query(sql, productRowMapper());
    }

    @Override
    public Product findProductById(Long id) {
        final String sql = "SELECT * FROM products WHERE id = ?";

        Product product = jdbcTemplate.queryForObject(sql, productRowMapper(), id);
        if (product == null) {
            throw new ProductNotFoundException(id);
        }
        return product;
    }

    @Override
    public void updateProduct(Long id, ProductRequestDto requestDto) {
        final String sql = "UPDATE products SET name = ?, price = ?, image_url = ? WHERE id = ?";

        int updated = jdbcTemplate.update(sql, requestDto.name(), requestDto.price(), requestDto.imageUrl(), id);
        if (updated == 0) {
            throw new ProductNotFoundException(id);
        }
    }

    @Override
    public void deleteProduct(Long id) {
        final String sql = "DELETE FROM products WHERE id = ?";

        int deleted = jdbcTemplate.update(sql, id);
        if (deleted == 0) {
            throw new ProductNotFoundException(id);
        }
    }

    private RowMapper<Product> productRowMapper() {
        return (rs, rowNum) -> new Product(
            rs.getLong("id"),
            rs.getString("name"),
            rs.getInt("price"),
            rs.getString("image_url")
        );
    }
}
