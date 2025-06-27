package gift.repository;

import gift.entity.Product;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductRepository{

    private final JdbcTemplate jdbcTemplate;
    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    Long nextId = 1L;

    public Product save(Product product) {

        Long id = nextId++;
        product.setId(id);

        String sql = "insert into products (id, name, price, imageUrl) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, product.getId(), product.getName(), product.getPrice(), product.getImageUrl());

        return product;
    }

    public Optional<Product> findById(Long productId) {
        String sql = "SELECT * FROM products WHERE id = ?";

        Product product = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Product.class), productId);
        return Optional.ofNullable(product);
    }

    public Optional<Product> update(Long id, String name, Integer price, String imageUrl) {

        Optional<Product> product = findById(id);

        if (product.isPresent()) {
            String sql = "UPDATE products SET name = ?, price = ?, imageUrl = ? WHERE id = ?";
            jdbcTemplate.update(sql, name, price, imageUrl, id);
        }

        return product;
    }

    public Optional<Product> deleteById(Long id) {
        Optional<Product> product = findById(id);
        if (product.isPresent()) {
            String sql = "DELETE FROM products WHERE id = ?";
            jdbcTemplate.update(sql, id);
        }

        return product;
    }

    public List<Product> findAll(int page, int size, String sortField, String sortDir) {

        List<String> sortFields = List.of("id", "name", "price");
        if (!sortFields.contains(sortField)) {
            sortField = "id";
        } // sortField 입력값이 없는 경우 기본값을 id로 설정

        String direction = "asc".equalsIgnoreCase(sortDir) ? "ASC" : "DESC";

        String sql = String.format(
                "SELECT * FROM products ORDER BY %s %s LIMIT ? OFFSET ?",
                sortField, direction
        );

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class), size, page * size);
    }
}
