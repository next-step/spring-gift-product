package gift.repository;

import gift.entity.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public class ProductRepository {
    private final JdbcClient jdbcClient;

    private final Map<Long, Product> products = new HashMap<>();
    private long sequence = 0L;

    public ProductRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }


    public Product save(Product product){
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcClient.sql("INSERT INTO product VALUES (:name, :price, :image_url)")
                .param("name", product.getName())
                .param("price", product.getPrice())
                .param("image_url", product.getImageUrl())
                .update(keyHolder, "id");

        Long newId = keyHolder.getKey().longValue();

        return new Product(newId, product.getName(), product.getPrice(), product.getImageUrl());
    }

    public Optional<Product> findById(Long id){
        return jdbcClient.sql("SELECT id, name, price, image_url FROM product WHERE id = :id")
                .param("id", id)
                .query(getProductRowMapper())
                .optional();
    }

    public List<Product> findAll(){
        return jdbcClient.sql("SELECT id, name, price, image_url FROM product")
                .query(getProductRowMapper())
                .list();
    }

    public void deleteById(Long id){
        jdbcClient.sql("DELETE FROM product WHERE id = :id")
                .param("id", id)
                .update();
    }

    private static RowMapper<Product> getProductRowMapper(){
        return (rs, rowNum) -> new Product(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getLong("price"),
                rs.getString("image_url")
        );
    }

}
