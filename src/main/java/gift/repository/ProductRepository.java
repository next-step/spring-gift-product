package gift.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import gift.domain.Product;

@Repository
public class ProductRepository {

    private final JdbcClient client;

    public ProductRepository(JdbcClient client) {
        this.client = client;
    }

    private static RowMapper<Product> getMemberRowMapper() {
        return (rs, rowNum) -> {
            var id = rs.getLong("id");
            var name = rs.getString("name");
            var price = rs.getInt("price");
            var imageUrl = rs.getString("imageUrl");
            return Product.of(id, name, price, imageUrl);
        };
    }

    public List<Product> findAll() {
        var sql = """
        SELECT id, name, price, imageUrl
        FROM product
        """;

        return client.sql(sql)
            .query(getMemberRowMapper())
            .list();
    }

    public Optional<Product> findById(Long id) {
        var sql = """
        SELECT id, name, price, imageUrl
        FROM product
        WHERE id = :id
        """;

        return client.sql(sql)
            .param("id", id)
            .query(getMemberRowMapper())
            .optional();
    }

    public Long save(Product product) {
        var sql = """
        INSERT INTO product (name, price, imageUrl)
        VALUES (:name, :price, :imageUrl)
        """;
        var keyHolder = new GeneratedKeyHolder();

        client.sql(sql)
            .param("name", product.getName())
            .param("price", product.getPrice())
            .param("imageUrl", product.getImageUrl())
            .update(keyHolder);

        if (keyHolder.getKey() == null) {
            return -1L;
        }

        return keyHolder.getKey().longValue();
    }

    public int update(Product product) {
        var sql = """
        UPDATE product
        SET name = :name, price = :price, imageUrl = :imageUrl
        WHERE id = :id
        """;

        return client.sql(sql)
            .param("id", product.getId())
            .param("name", product.getName())
            .param("price", product.getPrice())
            .param("imageUrl", product.getImageUrl())
            .update();
    }

    public int delete(Long id) {
        var sql = "DELETE FROM product WHERE id = :id";

        return client.sql(sql)
            .param("id", id)
            .update();
    }
}
