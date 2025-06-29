package gift.product.repository;

import gift.product.Product;
import gift.product.dto.ProductRequest;
import gift.product.dto.ProductUpdateRequest;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductRepository {
    private final JdbcClient jdbcClient;

    public ProductRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    private static Long nextId = 11L;

    public Optional<Product> save(ProductRequest request){
        Product product = new Product(nextId, request.name(), request.price(), request.imageUrl());

        String insertSql = "INSERT INTO product (id, name, price, image_url) VALUES (:id, :name, :price, :imageUrl)";
        jdbcClient.sql(insertSql)
                .param("id", nextId)
                .param("name", request.name())
                .param("price", request.price())
                .param("imageUrl", request.imageUrl())
                .update();
        nextId = nextId + 1;
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

    public int update(Long id, ProductUpdateRequest request){
        String updateSql = "UPDATE product SET name = :name, price = :price, image_url = :imageUrl WHERE id = :id";

        return jdbcClient.sql(updateSql)
                .param("id", id)
                .param("name", request.name())
                .param("price", request.price())
                .param("imageUrl", request.imageUrl())
                .update();
    }
}
