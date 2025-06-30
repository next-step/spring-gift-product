package gift.repository;

import gift.entity.Product;
import gift.exception.InvalidImageUrlException;
import gift.exception.InvalidNameException;
import gift.exception.InvalidPriceException;
import gift.exception.ProductNotFoundException;
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
    public Product updateProduct(Long id, String name, int price, String imageUrl) {
        String sql = "update products set name = ?, price = ?, imageUrl = ? where id = ?";
        int updatedRow = jdbcTemplate.update(sql, name, price, imageUrl, id);
        if(updatedRow == 0) {
            throw new ProductNotFoundException();
        }
        return findById(id);
    }

    public Product updatePrice(Long id, int price) {
        String sql = "update products set price = ? where id = ?";
        int updatedRows = jdbcTemplate.update(sql, price, id);
        if(updatedRows == 0) {
            throw new ProductNotFoundException();
        }
        return findById(id);
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM products WHERE id = ?";
        int deletedRows = jdbcTemplate.update(sql, id);
    }

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
