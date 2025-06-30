package gift.repository;

import gift.entity.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProductRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Product save(Product product) {

        String sql = "insert into product (name, imageUrl) values (?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, product.getName());
            ps.setString(2, product.getImageUrl());
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();

        if(key != null) {
            product.setId(key.longValue());
        }

        return product;
    }

    @Override
    public List<Product> findAll() {
        return jdbcTemplate.query("select * from product", getProductRowMapper());
    }

    @Override
    public Optional<Product> findById(Long id) {
        String sql = "select id, name, imageUrl from product where id = ?";

        return jdbcTemplate.query(sql, getProductRowMapper(), id)
                .stream()
                .findFirst();
    }

    @Override
    public void update(Product product) {
        String sql = "update product set name = ?, imageUrl = ? where id = ?";

        jdbcTemplate.update(sql, product.getName(), product.getImageUrl(), product.getId());
    }

    @Override
    public void deleteById(Long id) {
        String sql = "delete from product where id = ?";

        jdbcTemplate.update(sql, id);
    }

    private static RowMapper<Product> getProductRowMapper() {
        return (rs, rowNum) -> new Product(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("imageUrl")
        );
    }
}
