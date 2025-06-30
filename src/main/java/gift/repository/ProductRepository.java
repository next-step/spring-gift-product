package gift.repository;

import gift.entity.Product;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {

    private final JdbcClient jdbcClient;
    private final SimpleJdbcInsert jdbcInsert;

    public ProductRepository(JdbcClient jdbcClient, DataSource dataSource) {
        this.jdbcClient = jdbcClient;
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("product")
                .usingGeneratedKeyColumns("id");
    }

    public List<Product> findAll() {
        return jdbcClient.sql("SELECT * FROM product")
                .query(Product.class)
                .list();
    }

    public Product save(Product product) {
        var params = new java.util.HashMap<String, Object>();
        params.put("name", product.getName());
        params.put("price", product.getPrice());
        params.put("image_url", product.getImageUrl());

        Number generatedId = jdbcInsert.executeAndReturnKey(params);
        product.setId(generatedId.longValue());
        return product;
    }

    public Optional<Product> findById(Long id) {
        return jdbcClient.sql("SELECT * FROM product WHERE id = ?")
                .param(id)
                .query(Product.class)
                .optional();
    }

    public void update(Long id, Product updatedProduct) {
        jdbcClient.sql("UPDATE product SET name = ?, price = ?, image_url = ? WHERE id = ?")
                .params(updatedProduct.getName(), updatedProduct.getPrice(), updatedProduct.getImageUrl(), id)
                .update();
    }

    public void delete(Long id) {
        jdbcClient.sql("DELETE FROM product WHERE id = ?")
                .param(id)
                .update();
    }

    public boolean existsById(Long id) {
        Integer count = jdbcClient.sql("SELECT count(*) FROM product WHERE id = ?")
                .param(id)
                .query(Integer.class)
                .single();
        return count != null && count > 0;
    }
}
