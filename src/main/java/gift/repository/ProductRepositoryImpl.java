package gift.repository;

import gift.entity.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProductRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Product save(Product product) {

        if (product.getId() == null) {
            String sql = "insert into product (name, imageUrl) values (?, ?)";
            jdbcTemplate.update(sql, product.getName(), product.getImageUrl());
        } else {
            String sql2 = "update product set name = ?, imageUrl = ? where id = ?";
            jdbcTemplate.update(sql2, product.getName(), product.getImageUrl(), product.getId());
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
