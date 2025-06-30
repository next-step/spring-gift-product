package gift.repository;

import gift.entity.Product;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductRepository {
    private final JdbcClient jdbcClient;


    public ProductRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }
    public Product save(Product product) {
        var sql ="INSERT INTO product(name,price,imageUrl) Values (:name, :price, :imageUrl)";
        jdbcClient.sql(sql)
                .param("name", product.getName())
                .param("price", product.getPrice())
                .param("imageUrl", product.getImageUrl())
                .update();

        return product;
    }

    public List<Product> findAll() {
        var sql ="SELECT *  FROM  product";
        return jdbcClient.sql(sql).query(PRODUCT_ROW_MAPPER).list();
    }

//    private static RowMapper<Product> getProductRowMapper() {
//        return (rs, rowNum) -> {
//            var id = rs.getLong("id");
//            var name = rs.getString("name");
//            var price = rs.getInt("price");
//            var imageUrl = rs.getString("imageUrl");
//            return new Product(id, name, price, imageUrl);
//        };
//    }

 // 한번 생성해서 쓰기
    private static final RowMapper<Product> PRODUCT_ROW_MAPPER = (rs, rowNum) -> {
        var id = rs.getLong("id");
        var name = rs.getString("name");
        var price = rs.getInt("price");
        var imageUrl = rs.getString("imageUrl");
        return new Product(id, name, price, imageUrl);
    };


    public Optional<Product> findById(Long id) {
        String sql = "SELECT * FROM product WHERE id = :id";

        return jdbcClient.sql(sql)
                .param("id", id)
                .query(PRODUCT_ROW_MAPPER)
                .optional();
    }

    public Product update(Product product) {
        var sql ="UPDATE product SET name = :name, price = :price, imageUrl = :imageUrl WHERE id = :id";

         jdbcClient.sql(sql)
                .param("name", product.getName())
                .param("price", product.getPrice())
                .param("imageUrl", product.getImageUrl())
                .param("id", product.getId())
                .update();
         return product;
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM product WHERE id = :id";
        jdbcClient.sql(sql).param("id", id).update();
    }
}
