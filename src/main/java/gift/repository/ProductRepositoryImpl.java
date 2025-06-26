package gift.repository;

import gift.entity.Product;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private final JdbcTemplate jdbcTemplate;

    public ProductRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Product> findAllProducts() {
        String sql = """
                SELECT id, name, price, image_url
                FROM product
                """;

        return jdbcTemplate.query(sql, productRowMapper());
    }

    @Override
    public Product findProductByIdOrElseThrow(Long id) {
        String sql = """
                SELECT id, name, price, image_url
                FROM product
                WHERE id = ?
                """;
        List<Product> result = jdbcTemplate.query(sql, productRowMapper(), id);

        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 ID의 상품을 찾을 수 없습니다."));
    }

    @Override
    public Product createProduct(String name, Long price, String imageUrl) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);

        jdbcInsert
                .withTableName("product")
                .usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", name);
        parameters.put("price", price);
        parameters.put("image_url", imageUrl);
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new Product(key.longValue(), name, price, imageUrl);
    }

    @Override
    public void deleteProduct(Long id) {
        String sql = """
                DELETE
                FROM product
                WHERE id = ?
                """;

        jdbcTemplate.update(sql, id);
    }

    @Override
    public void updateProduct(Product product) {
        String sql = """
                UPDATE product
                SET name = ?, price = ?, image_url = ?
                WHERE id = ?
                """;
        jdbcTemplate.update(sql,
                product.getName(),
                product.getPrice(),
                product.getImageUrl(),
                product.getId());
    }

    private RowMapper<Product> productRowMapper() {
        return new RowMapper<Product>() {
            @Override
            public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Product(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getLong("price"),
                        rs.getString("image_url")
                );
            }
        };
    }
}
