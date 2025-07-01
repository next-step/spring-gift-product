// src/main/java/gift/repository/JdbcProductRepository.java
package gift.repository;

import gift.entity.Product;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcProductRepository implements ProductRepository {

    private final JdbcTemplate jdbc;
    private final SimpleJdbcInsert insert;
    private final RowMapper<Product> productRowMapper;

    // 생성자를 통해 ProductRowMapper를 주입받습니다.
    public JdbcProductRepository(JdbcTemplate jdbc, DataSource dataSource,
            ProductRowMapper productRowMapper) {
        this.jdbc = jdbc;
        this.insert = new SimpleJdbcInsert(dataSource)
                .withTableName("product")
                .usingGeneratedKeyColumns("id");
        this.productRowMapper = productRowMapper;
    }

    @Override
    public List<Product> findAll() {
        return jdbc.query("SELECT id, name, price, image_url FROM product", productRowMapper);
    }

    @Override
    public Optional<Product> findById(Long id) {
        List<Product> list = jdbc.query(
                "SELECT id, name, price, image_url FROM product WHERE id = ?", productRowMapper,
                id); // 사용
        return list.stream().findFirst();
    }

    @Override
    public boolean existsById(Long id) {
        Integer count = jdbc.queryForObject(
                "SELECT COUNT(*) FROM product WHERE id = ?", Integer.class, id);
        return count != null && count > 0;
    }

    @Override
    public Product save(Product product) {
        if (product.id() == null || product.id() <= 0) {
            return insertProduct(product);
        } else {
            return updateProduct(product);
        }
    }

    private Product insertProduct(Product product) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", product.name());
        params.put("price", product.price());
        params.put("image_url", product.imageUrl());
        Number key = insert.executeAndReturnKey(params);
        return product.withId(key.longValue());
    }

    private Product updateProduct(Product product) {
        int updated = jdbc.update(
                "UPDATE product SET name = ?, price = ?, image_url = ? WHERE id = ?",
                product.name(), product.price(), product.imageUrl(), product.id()
        );
        if (updated == 0) {
            throw new IllegalArgumentException("상품을 찾을 수 없습니다: " + product.id());
        }
        return product;
    }

    @Override
    public void deleteById(Long id) {
        jdbc.update("DELETE FROM product WHERE id = ?", id);
    }
}
