package gift.repository;

import gift.entity.Product;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {

    private final JdbcClient jdbcClient;

    public ProductRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Product> findAll() {
        return jdbcClient.sql("SELECT * FROM product")
                .query(Product.class)
                .list();
    }

    public Product save(Product product) {
        jdbcClient.sql("INSERT INTO product (name, price, image_url) VALUES (?, ?, ?)")
                .params(product.getName(), product.getPrice(), product.getImageUrl())
                .update();
        // insert 후 id 자동 반환이 필요하면 SimpleJdbcInsert 사용 가능 (추가 설명 필요 시 알려주세요!)
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
