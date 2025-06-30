package gift.repository;

import gift.entity.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private final JdbcTemplate jdbcTemplate;

    public ProductRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Product> findAllProducts() {
        String sql = "SELECT id, name, price, image_url FROM product";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Product(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getLong("price"),
                rs.getString("image_url")
        ));
    }

    @Override
    public Product saveProduct(Product product) {
        String sql = "INSERT INTO product (name, price, image_url) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, product.getName());
            ps.setLong(2, product.getPrice());
            ps.setString(3, product.getImageUrl());
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        if (key != null) {
            product.setId(key.longValue());
        }
        return product;
    }

    @Override
    public Product findProductById(Long id) {
        String sql = "SELECT id, name, price, image_url FROM product WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> new Product(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getLong("price"),
                rs.getString("image_url")
        ));
    }

    @Override
    public void updateProduct(Long id, String name, Long price, String imageUrl) {
        String sql = "UPDATE product SET name = ?, price = ?, image_url = ? WHERE id = ?";
        jdbcTemplate.update(sql, name, price, imageUrl, id);
    }

    @Override
    public void deleteProduct(Long id) {
        String sql = "DELETE FROM product WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
