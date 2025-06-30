package gift.repository;

import gift.entity.Product;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductRepository {

    private final JdbcClient jdbcClient;

    public ProductRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public Optional<Long> saveNewProduct(Product product) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("insert into product (name, price, image_url) values (:name, :price, :imageUrl)")
            .param("name", product.getName())
            .param("price", product.getPrice())
            .param("imageUrl", product.getImageUrl())
            .update(keyHolder);
        return Optional.ofNullable(keyHolder.getKeyAs(Long.class));
    }

    public Optional<Product> getProductById(Long id) {
        return jdbcClient.sql("select * from product where id = :id")
            .param("id", id)
            .query(getProductRowMapper())
            .optional();
    }

    public List<Product> getProductList() {
        return jdbcClient.sql("select * from product")
                .query(getProductRowMapper())
                .list();
    }

    public Optional<Product> updateProduct(Product product) {
        int updated = jdbcClient.sql("update product set name = :name, price = :price, image_url = :imageUrl where id = :id")
                .param("id", product.getId())
                .param("name", product.getName())
                .param("price", product.getPrice())
                .param("imageUrl", product.getImageUrl())
                .update();
        return updated == 1 ? Optional.of(product) : Optional.empty();
    }

    // return true when successfully deleted, false when id not exists.
    public boolean deleteProductById(Long id) {
        int updated = jdbcClient.sql("delete from product where id = :id")
                .param("id", id)
                .update();
        return updated == 1;
    }

    private static RowMapper<Product> getProductRowMapper() {
        return (rs, rowNum) -> {
            Long id = rs.getLong("id");
            String name = rs.getString("name");
            Integer price = rs.getInt("price");
            String imageUrl = rs.getString("image_url");
            return new Product(id, name, price, imageUrl);
        };
    }
}
