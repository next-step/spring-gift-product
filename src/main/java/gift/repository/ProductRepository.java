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

//    public Product saveProduct(String name, int price, String imageUrl) {
//        Product product = new Product(id++, name,price,imageUrl);
//        products.put(product.getId(),product);
//        return product;
//    }
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
