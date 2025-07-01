package gift.repository;

import gift.entity.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("JDBC-Repo")
public class ProductRepositoryJDBCImpl implements ProductRepository {

    private final JdbcClient jdbcClient;

    public ProductRepositoryJDBCImpl(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }


    private static final RowMapper<Product> ROW_MAPPER = (rs, rowNum) -> new Product(
        rs.getLong("id"),
        rs.getString("name"),
        rs.getInt("price"),
        rs.getString("image_url")
    );

    @Override
    public Product save(Product product) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcClient.sql("""
                INSERT INTO product (name, price, image_url) 
                VALUES (:name, :price, :imageUrl)
                """)
            .param("name", product.getName())
            .param("price", product.getPrice())
            .param("imageUrl", product.getImageUrl())
            .update(keyHolder);

        Long generatedId = (Long) keyHolder.getKey();

        return jdbcClient.sql("SELECT * FROM product WHERE id = :id")
            .param("id", generatedId)
            .query(ROW_MAPPER)
            .single();
    }

    @Override
    public List<Product> findAll() {
        return jdbcClient.sql("SELECT * FROM product")
            .query(ROW_MAPPER)
            .list();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return jdbcClient.sql("SELECT * FROM product WHERE id = :id")
            .param("id", id)
            .query(ROW_MAPPER)
            .optional();
    }

    @Override
    public Product update(Product product) {
        jdbcClient.sql(
                "UPDATE product SET name = :name, price = :price, image_url = :imageUrl WHERE id = :id")
            .param("id", product.getId())
            .param("name", product.getName())
            .param("price", product.getPrice())
            .param("imageUrl", product.getImageUrl())
            .update();

        return jdbcClient.sql("SELECT * FROM product WHERE id = :id")
            .param("id", product.getId())
            .query(ROW_MAPPER)
            .single();
    }

    @Override
    public void deleteById(Long id) {
        jdbcClient.sql("DELETE FROM product WHERE id = :id")
            .param("id", id)
            .update();
    }
}
