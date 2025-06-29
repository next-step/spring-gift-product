package gift.repository;

import gift.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;

@Repository
public class ProductRepository {

    private final JdbcClient jdbcClient;

    public ProductRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }


    private final Map<Long, Product> store = new HashMap<>();
    private Long nextId = 1L;


    public List<Product> findAll() {
        return jdbcClient.sql("SELECT * FROM product")
                .query(new RowMapper<Product>() {
                    @Override
                    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new Product(
                                rs.getLong("id"),
                                rs.getString("name"),
                                rs.getInt("price"),
                                rs.getString("image_url")
                        );
                    }
                })
                .list();
    }

    public Product save(Product product) {
        jdbcClient.sql("""
        INSERT INTO product (name, price, image_url)
        VALUES (:name, :price, :imageUrl)
    """)
                .param("name", product.getName())
                .param("price", product.getPrice())
                .param("imageUrl", product.getImageUrl())
                .update();

        return product;
    }


    public void update(Long id, Product updatedProduct) {
        Product existing = store.get(id);
        if (existing != null) {
            existing.setName(updatedProduct.getName());
            existing.setPrice(updatedProduct.getPrice());
            existing.setImageUrl(updatedProduct.getImageUrl());
        }
    }

    public Product findById(Long id) {
        return store.get(id);
    }

    public void deleteById(Long id) {
        store.remove(id);
    }



}
