package gift.repository;

import gift.domain.Product;
import gift.dto.ProductRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Product> rowMapper = (rs, rowNum) -> new Product(
        rs.getLong("id"),
        rs.getString("name"),
        rs.getInt("price"),
        rs.getString("image_url")
    );

    public ProductRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Product> findById(Long productId) {
        String sql = "select * from products where id = ?";

        return Optional.of(jdbcTemplate.queryForObject(sql, rowMapper, productId));
    }

    @Override
    public void save(Product product) {
        String sql = "insert into products(name, price, image_url) "
            + "values(?,?,?) ";

        jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getImageUrl());
    }

    public void update(ProductRequest product){
        String sql = "update products set name = ?, price = ?, image_url = ? where id = ?";
        jdbcTemplate.update(sql,product.name(), product.price(), product.imageUrl(), product.id());
    }

    @Override
    public void deleteById(Long productId) {
        String sql = "delete from products where id = ? ";
        jdbcTemplate.update(sql, productId);
    }

    @Override
    public List<Product> findAll() {
        String sql = "select * from products";
        return jdbcTemplate.query(sql, rowMapper);
    }
}
