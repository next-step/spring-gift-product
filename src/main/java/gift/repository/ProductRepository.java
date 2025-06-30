package gift.repository;

import gift.entity.Product;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

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

    // return updated row counts
    public int updateProduct(Product product) {
        return jdbcClient.sql("update product set name = :name, price = :price, image_url = :imageUrl where id = :id")
            .param("id", product.getId())
            .param("name", product.getName())
            .param("price", product.getPrice())
            .param("imageUrl", product.getImageUrl())
            .update();
    }

    // return updated row counts
    public int deleteProductById(Long id) {
        return jdbcClient.sql("delete from product where id = :id")
            .param("id", id)
            .update();
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
