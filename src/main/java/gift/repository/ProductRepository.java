package gift.repository;

import gift.entity.Product;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {

    private final JdbcClient client;

    public ProductRepository(JdbcClient client) {
        this.client = client;
    }

    public Optional<Product> save(Product product) {
        var sql = "insert into product (name, price, imageUrl) values (:name, :price, :imageUrl);";
        var keyHolder = new GeneratedKeyHolder();
        client.sql(sql)
                .param("name", product.getName())
                .param("price", product.getPrice())
                .param("imageUrl", product.getImageUrl())
                .update(keyHolder);
        Long id = keyHolder.getKey().longValue();
        return Optional.of(new Product(id, product.getName(), product.getPrice(), product.getImageUrl()));
        //return findById(id);
    }

    public Optional<Product> findById(Long id) {
        var sql = "select * from product where id=:id;";
        return client.sql(sql)
                .param("id", id)
                .query(getRowMapper())
                .optional();
    }

    public List<Product> findAll() {
        var sql = "select * from product;";
        return client.sql(sql)
                .query(getRowMapper())
                .list();
    }

    public Optional<Product> update(Long id, Product product) {
        var sql = "update product set name = :name, price = :price, imageUrl = :imageUrl where id = :id;";
        var affected = client.sql(sql)
                .param("id", product.getId())
                .param("name", product.getName())
                .param("price", product.getPrice())
                .param("imageUrl", product.getImageUrl())
                .update();

        if(affected == 0) {
            return Optional.empty();
        }
        return Optional.of(new Product(id, product.getName(), product.getPrice(), product.getImageUrl()));
    }

    public void delete(Long id) {
        var sql = "delete from product where id = :id;";
        client.sql(sql)
                .param("id", id)
                .update();
    }

    private static RowMapper<Product> getRowMapper() {
        return (rs, rowNum) -> {
            Long id = rs.getLong("id");
            String name = rs.getString("name");
            Long price = (long) rs.getInt("price");
            String imageUrl = rs.getString("imageUrl");
            return new Product(id, name, price, imageUrl);
        };
    }
}
