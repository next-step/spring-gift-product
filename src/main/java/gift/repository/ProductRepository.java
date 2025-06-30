package gift.repository;

import gift.domain.Product;
import gift.dto.ProductRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Product> rowMapper = (rs, rowNum) -> new Product(
        rs.getLong("id"),
        rs.getString("name"),
        rs.getInt("price"),
        rs.getString("image_url")
    );
    private final KeyHolder keyHolder = new GeneratedKeyHolder();

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Product> findById(Long productId) {
        String sql = "select * from products where id = ?";

        return Optional.of(jdbcTemplate.queryForObject(sql, rowMapper, productId));
    }

    public Long save(Product product) {
        String sql = "insert into products(name, price, image_url) "
            + "values(?,?,?) ";

        jdbcTemplate.update((Connection con)->{
            PreparedStatement st= con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            st.setString(1, product.getName());
            st.setInt(2, product.getPrice());
            st.setString(3, product.getImageUrl());
            return st;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    public void update(ProductRequest product){
        String sql = "update products set name = ?, price = ?, image_url = ? where id = ?";
        jdbcTemplate.update(sql,product.name(), product.price(), product.imageUrl(), product.id());
    }

    public void deleteById(Long productId) {
        String sql = "delete from products where id = ? ";
        jdbcTemplate.update(sql, productId);
    }

    public List<Product> findAll() {
        String sql = "select * from products";
        return jdbcTemplate.query(sql, rowMapper);
    }
}
