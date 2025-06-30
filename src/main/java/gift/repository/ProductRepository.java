package gift.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
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
        var sql = "SELECT id, name, price, imageUrl FROM product";
        return client.sql(sql)
            .query(getMemberRowMapper())
            .stream()
            .toList();
    }

    public Optional<Product> findById(Long id) {
        var sql = "SELECT id, name, price, imageUrl FROM product WHERE id = :id";
        return client.sql(sql)
            .param("id", id)
            .query(getMemberRowMapper())
            .optional();
    }

    public void save(Product product) {
        var sql = "MERGE INTO product (id, name, price, imageUrl) KEY(id) VALUES (:id, :name, :price, :imageUrl)";
        client.sql(sql)
            .param("id", product.getId())
            .param("name", product.getName())
            .param("price", product.getPrice())
            .param("imageUrl", product.getImageUrl())
            .update();
    }

    public void deleteById(Long id) {
        var sql = "DELETE FROM product WHERE id = :id";
        client.sql(sql)
            .param("id", id)
            .update();
    }
}
