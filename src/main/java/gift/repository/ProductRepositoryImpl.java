package gift.repository;

import gift.domain.Product;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository{

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final JdbcClient jdbcClient;

    public ProductRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate, JdbcClient jdbcClient) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Product save(Product product) {
        String sql = """
            INSERT INTO products (name, price, image_url)
            VALUES (:name, :price, :imageUrl)
        """;

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", product.getName())
                .addValue("price", product.getPrice())
                .addValue("imageUrl", product.getImageUrl());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, params, keyHolder);

        product.setId(keyHolder.getKey().longValue());
        return product;
    }

    @Override
    public List<Product> findAll() {
        return jdbcClient.sql("SELECT * FROM products")
                .query(Product.class)
                .list();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return jdbcClient.sql("SELECT * FROM products WHERE id = :id")
                .param("id", id)
                .query(Product.class)
                .optional();
    }

    @Override
    public void deleteById(Long id) {
        jdbcClient.sql("DELETE FROM products WHERE id = :id")
                .param("id", id)
                .update();
    }

    @Override
    public boolean existsById(Long id) {
        Integer count = jdbcClient.sql("SELECT COUNT(*) FROM products WHERE id = :id")
                .param("id", id)
                .query(Integer.class)
                .single();

        return count != null && count > 0;
    }

    @Override
    public void updateById(Long id, Product product) {
        jdbcClient.sql("""
            UPDATE products
            SET name = :name, price = :price, image_url = :imageUrl
            WHERE id = :id
        """)
                .param("name", product.getName())
                .param("price", product.getPrice())
                .param("imageUrl", product.getImageUrl())
                .param("id", id)
                .update();
    }
}
