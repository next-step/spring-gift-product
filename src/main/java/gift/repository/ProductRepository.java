package gift.repository;

import gift.dto.response.ProductResponseDto;
import gift.entity.Product;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository implements ProductRepositoryInterface {

    private final JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //상품 반환용 매퍼
    private RowMapper<Product> productRowMapper() {
        return new RowMapper<>() {

            @Override
            public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Product(
                    rs.getLong("productId"),
                    rs.getString("name"),
                    rs.getInt("price"),
                    rs.getString("imageURL")
                );
            }
        };
    }

    @Override
    public Optional<Product> findById(long productId) {
        return jdbcTemplate.query("select * from product where productId = ?",
            productRowMapper(),
            productId).stream().findFirst();
    }

    @Override
    public void add(Product product) {

    }

    @Override
    public void update(Product product) {

    }

    @Override
    public void delete(long productId) {

    }

    @Override
    public Collection<ProductResponseDto> findAll() {
        return List.of();
    }

    @Override
    public boolean containsKey(long id) {
        return false;
    }
}