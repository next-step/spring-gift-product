package gift.repository;

import gift.entity.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepository {
    private final JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Product findById(Long id) {
       String sql = "select id, name, price,imageUrl from products where id = ?";
        return jdbcTemplate.queryForObject(
            sql, (result,rowNum) -> {
                Product product = new Product(
                    result.getLong("id"),
                    result.getString("name"),
                    result.getInt("price"),
                    result.getString("imageUrl")
                );
                return product;
            }
        ,id);
    }

    public Product saveProduct(String name, int price, String imageUrl) {
        String sql = "INSERT INTO products(name,price,imageUrl) VALUES(?,?,?)";
        jdbcTemplate.update(sql, name, price, imageUrl);

        String sqlForFind = "select id, name, price, imageUrl "
            + "from products where name = ? AND price = ? AND imageUrl = ?";
        return jdbcTemplate.queryForObject(
            sqlForFind, (result,rowNum) -> {
                Product product = new Product(
                    result.getLong("id"),
                    result.getString("name"),
                    result.getInt("price"),
                    result.getString("imageUrl")
                );
            return product;
            }
        ,name,price,imageUrl);
    }
//
//    public void deleteById(Long id) {
//        products.remove(id);
//    }

    public List<Product> findAllProducts() {
        String sql = "select id, name, price, imageUrl from products";
        List<Product> productList = jdbcTemplate.query(
            sql, (resultSet,rowNum) -> {
                Product product = new Product(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("price"),
                    resultSet.getString("imageUrl")
                );
                return product;
            });
        return productList;
    }
}
