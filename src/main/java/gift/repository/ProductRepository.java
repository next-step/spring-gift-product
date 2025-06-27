package gift.repository;

import gift.entity.Product;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductRepository {
    private final Map<Long, Product> products = new HashMap<>();
    private final JdbcClient jdbcClient;
    private long Id = 1;

    public ProductRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }
    public Product save(Product product) {
        if (product.getId() == null) {
            product.setId(Id++);
        }
        products.put(product.getId(), product);
        var sql ="insert into Product (id, name,price,imageUrl) values (:id, :name, :price, :imageUrl)";
        jdbcClient.sql(sql)
                .param("id", product.getId())
                .param("name", product.getName())
                .param("price", product.getPrice())
                .param("imageUrl", product.getImageUrl())
                .update();
        return product;
    }

    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }


    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(products.get(id));
    }


    public void deleteById(Long id) {
        products.remove(id);
    }
}
