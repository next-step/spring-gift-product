package gift.product.repository;

import gift.product.Product;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductRepository {
    private final JdbcClient jdbcClient;

    public ProductRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public Optional<Product> save(Product product){
        String insertSql = "INSERT INTO product (name, price, image_url) VALUES (:name, :price, :imageUrl)";
        jdbcClient.sql(insertSql)
                .param("name", product.getName())
                .param("price", product.getPrice())
                .param("imageUrl", product.getImageUrl())
                .update();
        return Optional.of(product);
    }

    public Optional<Product> get(Long id) {
        String sql = "SELECT * FROM product WHERE id = :id";
        return jdbcClient.sql(sql)
                .param("id", id)
                .query(Product.class)
                .optional();
    }

    public void delete(Long id){
        String sql = "DELETE FROM product WHERE id = :id";
        jdbcClient.sql(sql)
                .param("id", id)
                .update();
    }

    public Optional<List<Product>> getAll(){
        String sql = "SELECT * FROM product";
        return Optional.of(
                jdbcClient.sql(sql)
                .query(Product.class)
                .list()
        );
    }

    public int update(Product product){
        String updateSql = "UPDATE product SET name = :name, price = :price, image_url = :imageUrl WHERE id = :id";

        return jdbcClient.sql(updateSql)
                .param("id", product.getId())
                .param("name", product.getName())
                .param("price", product.getPrice())
                .param("imageUrl", product.getImageUrl())
                .update();
    }
}
