package gift.dao;

import gift.entity.Product;
import gift.exception.DBServerException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDao {
    private final JdbcClient jdbcClient;

    private static class ProductRowMapper implements RowMapper<Product> {
        @Override
        public Product mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
            return new Product(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getLong("price"),
                    rs.getString("image_url")
            );
        }
    }

    public ProductDao(JdbcClient client) {;
        this.jdbcClient = client;
    }

    public List<Product> findAll() {
        String sql = "SELECT * FROM products";
        return jdbcClient.sql(sql)
                .query(new ProductRowMapper())
                .stream()
                .toList();
    }

    public Product findById(Long productId) {
        String sql = "SELECT * FROM products WHERE id = ?";
        return jdbcClient.sql(sql)
                .param(productId)
                .query(new ProductRowMapper())
                .stream()
                .findFirst()
                .orElse(null);
    }

    public Long insertWithKey(Product product) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO products (name, price, image_url) VALUES (?, ?, ?)";
        jdbcClient.sql(sql)
            .param(product.getName())
            .param(product.getPrice())
            .param(product.getImageUrl())
            .update(keyHolder);
        if (keyHolder.getKey() == null) {
            throw new DBServerException("상품을 저장하는 중 오류가 발생했습니다.(key에 제대로 생성되지 않았음)");
        }
        return keyHolder.getKey().longValue();
    }

    public int update(Product product) {
        String sql = "UPDATE products SET name = ?, price = ?, image_url = ? WHERE id = ?";
        return jdbcClient.sql(sql)
                .param(product.getName())
                .param(product.getPrice())
                .param(product.getImageUrl())
                .param(product.getId())
                .update();
    }

    public int updateNameById(Long productId, String name) {
        String sql = "UPDATE products SET name = ? WHERE id = ?";
        return jdbcClient.sql(sql)
                .param(name)
                .param(productId)
                .update();
    }

    public int updatePriceById(Long productId, Long price) {
        String sql = "UPDATE products SET price = ? WHERE id = ?";
        return jdbcClient.sql(sql)
                .param(price)
                .param(productId)
                .update();
    }

    public int updateImageUrlById(Long productId, String imageUrl) {
        String sql = "UPDATE products SET image_url = ? WHERE id = ?";
        return jdbcClient.sql(sql)
                .param(imageUrl)
                .param(productId)
                .update();
    }

    public int deleteById(Long productId) {
        String sql = "DELETE FROM products WHERE id = ?";
        return jdbcClient.sql(sql)
                .param(productId)
                .update();
    }
}
