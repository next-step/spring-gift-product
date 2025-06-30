package gift.repository;

import gift.entity.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final JdbcClient jdbcClient;

    public ProductRepositoryImpl(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    private static final RowMapper<Product> PRODUCT_ROW_MAPPER = (rs, rowNum) -> new Product(
        rs.getLong("id"),
        rs.getString("name"),
        rs.getInt("price"),
        rs.getString("image_url")
    );

    @Override
    public Product save(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("product not found");
        }

        if (product.getName() == null || product.getPrice() == null) {
            throw new IllegalArgumentException("Required fields are missing");
        }

        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = "INSERT INTO products (name, price, image_url) VALUES (?, ?, ?)";
        jdbcClient.sql(sql)
            .param(1, product.getName())
            .param(2, product.getPrice())
            .param(3, product.getImageUrl())
            .update(keyHolder, new String[]{"id"});

        Number key = keyHolder.getKey();
        if (key == null) {
            throw new IllegalArgumentException("Failed to get id");
        }
        Long id = key.longValue();

        return new Product(
            id,
            product.getName(),
            product.getPrice(),
            product.getImageUrl()
        );
    }

    @Override
    public Product update(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("product not found");
        }

        if (product.getId() == null || product.getName() == null || product.getPrice() == null) {
            throw new IllegalArgumentException("Required fields are missing");
        }

        String sql = "UPDATE products SET name = ?, price = ?, image_url = ? WHERE id = ?";
        int rowsCount = jdbcClient.sql(sql)
            .param(1, product.getName())
            .param(2, product.getPrice())
            .param(3, product.getImageUrl())
            .param(4, product.getId())
            .update();
        if (rowsCount == 0) {
            throw new IllegalArgumentException("Product(id: " + product.getId() + ") not found");
        }

        return product;
    }

    @Override
    public Optional<Product> findById(Long id) {
        String sql = "SELECT id, name, price, image_url FROM products WHERE id = ?";
        return jdbcClient.sql(sql)
            .param(1, id)
            .query(Product.class)
            .optional();
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM products WHERE id = ?";
        int rowsCount = jdbcClient.sql(sql)
            .param(1, id)
            .update();
        if (rowsCount == 0) {
            throw new IllegalArgumentException("Product(id: " + id + " ) not found");
        }
    }

    @Override
    public List<Product> findAll() {
        String sql = "SELECT id, name, price, image_url FROM products";
        return jdbcClient.sql(sql)
            .query(Product.class)
            .list();
    }

}
