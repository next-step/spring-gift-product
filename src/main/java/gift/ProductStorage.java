package gift;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductStorage {
    private final JdbcClient jdbc;

    public ProductStorage(JdbcClient jdbcClient) {
        this.jdbc = jdbcClient;
    }

    public List<Product> getProducts() {
        return jdbc.sql("SELECT * FROM product")
                .query(Product.class).list();
    }

    public Optional<Product> findById(Long id) {
        return jdbc.sql("SELECT * FROM product WHERE id = :id")
                .param("id", id)
                .query(Product.class).optional();
    }

    public Product save(Product product) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.sql("INSERT INTO product (name, price, image_url) VALUES (:name, :price, :image_url)")
                .param("name", product.getName())
                .param("price", product.getPrice())
                .param("image_url", product.getImageUrl()).update(keyHolder);

        product.setId(keyHolder.getKey().longValue());

        return product;
    }

    public void update(Long id, Product updated) {
        jdbc.sql("UPDATE product SET name=:name, price=:price, image_url=:image_url WHERE id=:id")
                .param("name", updated.getName())
                .param("price", updated.getPrice())
                .param("image_url", updated.getImageUrl())
                .param("id", id).update();
    }

    public void delete(Long id) {
        jdbc.sql("DELETE FROM product WHERE id = :id")
                .param("id", id).update();
    }
}
