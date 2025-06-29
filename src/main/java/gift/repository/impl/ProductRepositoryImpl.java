package gift.repository.impl;

import gift.dto.ProductRequestDto;
import gift.model.Product;
import gift.repository.ProductRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProductRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Product> productRowMapper = new RowMapper<Product>() {
        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Product(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getInt("price"),
                    rs.getString("image_url")
            );
        }
    };

    @Override
    public Product save(Product product) {
        String sql = "INSERT INTO products (name, price, image_url) VALUES (?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, product.getName());
                ps.setInt(2, product.getPrice());
                ps.setString(3, product.getImageUrl());
                return ps;
            }
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            product.setId(keyHolder.getKey().longValue());
        }

        return product;
    }

    @Override
    public List<Product> findAll() {
        String sql = "SELECT id, name, price, image_url FROM products ORDER BY id";
        return jdbcTemplate.query(sql, productRowMapper);
    }

    @Override
    public Optional<Product> findById(Long id) {
        String sql = "SELECT id, name, price, image_url FROM products WHERE id = ?";
        List<Product> products = jdbcTemplate.query(sql, productRowMapper, id);
        return products.isEmpty() ? Optional.empty() : Optional.of(products.get(0));
    }

    @Override
    public Product update(Long id, ProductRequestDto productRequestDto) {
        String sql = "UPDATE products SET name = ?, price = ?, image_url = ? WHERE id = ?";  // 쉼표 제거

        int updatedRows = jdbcTemplate.update(sql,
                productRequestDto.name(),
                productRequestDto.price(),
                productRequestDto.imageUrl(),
                id);

        if (updatedRows == 0) {
            throw new RuntimeException("상품을 찾을 수 없습니다. ID: " + id);
        }

        return findById(id).orElseThrow(() ->
                new RuntimeException("업데이트 후 상품을 찾을 수 없습니다. ID: " + id));
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM products WHERE id = ?";
        int deletedRows = jdbcTemplate.update(sql, id);

        if (deletedRows == 0) {
            throw new RuntimeException("상품을 찾을 수 없습니다. ID: " + id);
        }
    }
}
