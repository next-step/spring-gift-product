package gift.repository;

import gift.dto.Product;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {
    private final JdbcClient jdbc;

    public ProductRepository(JdbcClient jdbc) {
        this.jdbc = jdbc;
    }

    public List<Product> findAll() {
        return jdbc.sql("SELECT * FROM product")
                .query(Product.class)
                .list();
    }

    public Optional<Product> findById(Long id) {
        return jdbc.sql("SELECT * FROM product WHERE id= :id")
                .param("id", id)
                .query(Product.class)
                .optional();
    }

    public Product save(Product product) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.sql("""
                    INSERT INTO product (name, price, image_url)
                    VALUES (:name, :price, :image_url)
                   """)
                .param("name", product.getName())
                .param("price", product.getPrice())
                .param("image_url",product.getImageUrl())
                .update(keyHolder);
        Long id = keyHolder.getKey().longValue();
        product.setId(id);
        return product;
    }

    public void update(Product product) {
        jdbc.sql("""
                    Update product
                    set name = :name, price = :price, image_url = :image_url
                    where id = :id
                """)
                .param("name", product.getName())
                .param("price", product.getPrice())
                .param("image_url", product.getImageUrl())
                .param("id", product.getId())
                .update();
    }

    public void delete(Long id) {
        jdbc.sql("DELETE FROM product WHERE id = :id")
                .param("id", id)
                .update();
    }
}

