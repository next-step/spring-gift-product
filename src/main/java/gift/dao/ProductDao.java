package gift.dao;

import gift.Entity.Product;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDao {
    private final JdbcClient client;

    public ProductDao(JdbcClient client) {
        this.client = client;
    }

    public void insertProduct(Product product) {
        var sql = """
            insert into products (id, name, price, imageUrl) 
            values (:id, :name, :price, :imageUrl)
        """;
        client.sql(sql)
                .param("id", product.getId())
                .param("name", product.getName())
                .param("price", product.getPrice())
                .param("imageUrl", product.getImageUrl())
                .update();
    }

    public Product selectProduct(Long id) {
        var sql = "select id, name, price, imageUrl from product where id = :id";
        return client.sql(sql)
                .param("id",id)
                .query(getProductRowMapper())
                .single();
    }

    public List<Product> showProducts() {
        var sql = "SELECT id, name, price, imageUrl FROM products";
        client.sql(sql)
                .query(getProductRowMapper())
                .list();
    }

    public void updateProduct(Long id,Product product) {
        var sql = """
                UPDATE products SET name = :name, price = :price, imageUrl = :imageUrl WHERE id = :id
                """;
        client.sql(sql)
                .param("id", id)
                .param("name", product.getName())
                .param("price", product.getPrice())
                .param("imageUrl", product.getImageUrl())
                .update();
    }

    public void deleteProduct(Long id) {
        var sql = """
                DELETE FROM products WHERE id = :id
        """;
        client.sql(sql)
                .param("id", id)
                .update();
    }

    private RowMapper<Product> getProductRowMapper() {
        return (rs, rowNum) -> {
            var id = rs.getLong("id");
            var name = rs.getString("name");
            var price = rs.getInt("price");
            var imageUrl = rs.getString("imageUrl");
            return new Product(id, name, price, imageUrl);
        };
    }


}
