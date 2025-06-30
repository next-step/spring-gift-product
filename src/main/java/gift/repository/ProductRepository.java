package gift.repository;

import gift.model.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

    private JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 상품 저장
    public Product save(Product product) {
        String sql = "INSERT INTO PRODUCT(name, price, imageUrl) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getImageUrl());
        return product;
    }

    // 상품 전체 조회
    public List<Product> findAll() {
        String sql = "SELECT id, name, price, imageUrl FROM PRODUCT";
        return jdbcTemplate.query(sql, productRowMapper());
    }

    // 상품 단건 조회
    public Optional<Product> findById(Long id) {
        String sql = "SELECT id, name, price, imageUrl FROM PRODUCT WHERE id = ?";
        return Optional.of(jdbcTemplate.queryForObject(sql, productRowMapper(), id));
    }

    // 상품 수정
    public void update(Product product) {
        String sql = "UPDATE PRODUCT SET name = ?, price = ?, imageUrl = ? WHERE id = ?";
        jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getImageUrl(),
            product.getId());
    }

    // 상품 삭제
    public void delete(Long id) {
        String sql = "DELETE FROM PRODUCT WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private RowMapper<Product> productRowMapper() {
        return ((rs, rowNum) -> {
            Long id = rs.getLong("id");
            String name = rs.getString("name");
            int price = rs.getInt("price");
            String imageUrl = rs.getString("imageUrl");
            return new Product(id, name, price, imageUrl);
        });
    }

}
