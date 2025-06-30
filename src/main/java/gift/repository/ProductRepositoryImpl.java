package gift.repository;

import gift.domain.Product;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository{

    private final JdbcClient jdbcClient;

    public ProductRepositoryImpl(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Product save(Product product) {
        jdbcClient.sql("""
                INSERT INTO products (name, price, image_url)
                VALUES (:name, :price, :imageUrl)
            """)
                .param("name", product.getName())
                .param("price", product.getPrice())
                .param("imageUrl", product.getImageUrl())
                .update();

        Long id = jdbcClient.sql("SELECT IDENTITY()")
                .query(Long.class)
                .single();

        product.setId(id);
        return product;
    }

    @Override
    public List<Product> findAll() {
        return jdbcClient.sql("SELECT * FROM products")
                .query(Product.class)
                .list();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return jdbcClient.sql("SELECT * FROM products WHERE id = :id")
                .param("id", id)
                .query(Product.class)
                .optional();
    }

    @Override
    public void deleteById(Long id) {
        jdbcClient.sql("DELETE FROM products WHERE id = :id")
                .param("id", id)
                .update();
    }

    @Override
    public boolean existsById(Long id) {
        Integer count = jdbcClient.sql("SELECT COUNT(*) FROM products WHERE id = :id")
                .param("id", id)
                .query(Integer.class)
                .single();

        return count != null && count > 0;
    }
}
