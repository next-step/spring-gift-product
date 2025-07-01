package gift.repository;

import gift.entity.Product;
import gift.exception.InvalidImageUrlException;
import gift.exception.InvalidNameException;
import gift.exception.InvalidPriceException;
import gift.exception.ProductNotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepository {
    private final JdbcClient jdbcClient;

    public ProductRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }


    public Product findById(Long id) {
        String sql = "select id, name, price, imageUrl from products where id = ?";
        return jdbcClient.sql(sql)
            .param(id)
            .query((result,rowNum) -> {
                Product product = new Product(
                    result.getLong("id"),
                    result.getString("name"),
                    result.getInt("price"),
                    result.getString("imageUrl")
                );
                return product;
            })
            .single();
    }

    public Product saveProduct(String name, int price, String imageUrl) {
        String sql = "INSERT INTO products(name,price,imageUrl) VALUES(?,?,?)";
        jdbcClient.sql(sql)
            .param(name)
            .param(price)
            .param(imageUrl)
            .update();

        String sqlForFind = "SELECT id, name, price, imageUrl FROM products"
            + " WHERE name = ? AND price = ? AND imageUrl = ?";
        return jdbcClient.sql(sqlForFind)
            .param(name)
            .param(price)
            .param(imageUrl)
            .query((result,rowNum) -> {
                Product product = new Product(
                    result.getLong("id"),
                    result.getString("name"),
                    result.getInt("price"),
                    result.getString("imageUrl")
                );
                return product;
            })
            .single();
    }

    public Product updateProduct(Long id, String name, int price, String imageUrl) {
        String sql = "update products set name = ?, price = ?, imageUrl = ? where id = ?";
        int updatedRow = jdbcClient.sql(sql)
                        .param(name)
                        .param(price)
                        .param(imageUrl)
                        .param(id)
                        .update();
        if(updatedRow == 0) {
            throw new ProductNotFoundException();
        }
        return findById(id);
    }

    public Product updatePrice(Long id, int price) {
        String sql = "update products set price = ? where id = ?";
        int updatedRows = jdbcClient.sql(sql)
            .param(price)
            .param(id)
            .update();

        if(updatedRows == 0) {
            throw new ProductNotFoundException();
        }

        return findById(id);
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM products WHERE id = ?";
        int deletedRows = jdbcClient.sql(sql)
            .param(id)
            .update();

        if(deletedRows == 0) {
            throw new ProductNotFoundException();
        }
    }

    public List<Product> findAllProducts() {
        return jdbcClient.sql("select id, name, price, imageUrl from products")
            .query((resultSet, rowNum) -> {
                Product product = new Product(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("price"),
                    resultSet.getString("imageUrl")
                );
                return product;
            })
            .list();
    }
}
