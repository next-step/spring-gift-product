package gift.repository;

import gift.entity.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public class ProductRepository {
    private final JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Product save(Product product) {
        String sql = "INSERT INTO products(name, price, image_url) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getImageUrl());
        return product;
    }

    public List<Product> findAll() {
        String sql = "SELECT * FROM products";

        return jdbcTemplate.query(sql, (rs, rn) ->
                new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        new BigInteger(rs.getString("price")),
                        rs.getString("image_url")
                )
        );
    }

    public Product findById(Integer id) {
        String sql = "SELECT * FROM products WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rn) ->
                new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        new BigInteger(rs.getString("price")),
                        rs.getString("image_url")
                ), id);
    }

    public void update(Integer id, Product product) {
        String sql = "UPDATE products SET name = ?, price = ?, image_url = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                product.getName(),
                product.getPrice(),
                product.getImageUrl(),
                id);
    }

    public void delete(Integer id) {
        String sql = "DELETE FROM products WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
