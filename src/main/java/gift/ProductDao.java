package gift;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ProductDao {
    private final JdbcClient jdbcClient;

    public ProductDao(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public void save(ProductDto productDto) {
        jdbcClient.sql("INSERT INTO PRODUCTS (name, price, imageUrl) VALUES (:name, :price, :imageUrl)")
                .param("name", productDto.getName())
                .param("price", productDto.getPrice())
                .param("imageUrl", productDto.getImageUrl())
                .update();
    }


    public List<Product> getAll() {
        return jdbcClient.sql("SELECT * FROM PRODUCTS")
                .query(Product.class)
                .list();
    }

    public Product findById(Long id) throws EmptyResultDataAccessException {
        return jdbcClient.sql("SELECT * FROM PRODUCTS WHERE id = :id")
                .param("id", id)
                .query(Product.class)
                .single();
    }

    @Transactional
    public void update(Long id, ProductDto productDto) {
        if (productDto.getName() != null) {
            jdbcClient.sql("UPDATE PRODUCTS SET name = :name WHERE id = :id")
                    .param("name", productDto.getName())
                    .param("id", id)
                    .update();
        }
        if (productDto.getPrice() != 0) {
            jdbcClient.sql("UPDATE PRODUCTS SET price = :price WHERE id = :id")
                    .param("price", productDto.getPrice())
                    .param("id", id)
                    .update();
        }
        if (productDto.getImageUrl() != null) {
            jdbcClient.sql("UPDATE PRODUCTS SET imageUrl = :imageUrl WHERE id = :id")
                    .param("imageUrl", productDto.getImageUrl())
                    .param("id", id)
                    .update();
        }
    }

    public void delete(Long id) {
        jdbcClient.sql("DELETE FROM PRODUCTS WHERE id = :id")
                .param("id", id)
                .update();
    }

}
