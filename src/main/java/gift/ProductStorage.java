package gift;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductStorage {
    private final JdbcClient products;

    public ProductStorage(JdbcClient products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products.sql("SELECT * FROM product")
                .query(Product.class).list();
    }

    public Optional<Product> findById(Long id) {
        return products.sql("SELECT * FROM product WHERE id = :id")
                .param("id", id)
                .query(Product.class).optional();
    }

    public Product save(Product product) {
        products.sql("INSERT INTO product (name, price, image_url) VALUES (:name, :price, :image_url)")
                .param("name", product.getName())
                .param("price", product.getPrice())
                .param("image_url", product.getImageUrl()).update();

        Long id = products.sql("SELECT SCOPE_IDENTITY()").query(Long.class).single();

        product.setId(id);
        return product;
    }

    public void update(Long id, Product updated) {
        products.sql("UPDATE product SET name=:name, price=:price, imageURL=:imageUrl WHERE id=:id")
                .param("name", updated.getName())
                .param("price", updated.getPrice())
                .param("imageUrl", updated.getImageUrl())
                .param("id", id).update();
    }
    public void delete(Long id) {
        products.sql("DELETE FROM product WHERE id = :id")
                .param("id", id).update();
    }
}
