package gift.repository;

import gift.entity.Product;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.*;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private final JdbcClient jdbcClient;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public ProductRepositoryImpl(DataSource dataSource) {
        this.jdbcClient = JdbcClient.create(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
            .withTableName("product")
            .usingGeneratedKeyColumns("id");
    }

    @Override
    public Product saveProduct(Product product) {
        Map<String, Object> parameters = Map.of(
            "name", product.getName(),
            "price", product.getPrice(),
            "image_url", product.getImageUrl()
        );

        Long id = simpleJdbcInsert.executeAndReturnKey(parameters).longValue();
        return new Product(id, product.getName(), product.getPrice(), product.getImageUrl());
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        String sql = "SELECT id, name, price, image_url FROM product WHERE id = ?";

        return jdbcClient.sql(sql)
            .param(id)
            .query(Product.class)
            .optional();
    }

    @Override
    public List<Product> findAllProducts() {
        String sql = "SELECT id, name, price, image_url FROM product";

        return jdbcClient.sql(sql)
            .query(Product.class)
            .list();
    }

    @Override
    public void updateProduct(Product product) {
        String sql = "UPDATE product SET name = ?, price = ?, image_url = ? WHERE id = ?";
        jdbcClient.sql(sql)
            .param(product.getName())
            .param(product.getPrice())
            .param(product.getImageUrl())
            .param(product.getId())
            .update();
    }

    @Override
    public void deleteProductById(Long id) {
        String sql = "DELETE FROM product WHERE id = ?";
        jdbcClient.sql(sql)
            .param(id)
            .update();
    }

    @Override
    public void deleteAllProducts() {
        String sql = "DELETE FROM product";
        jdbcClient.sql(sql)
            .update();
    }
}