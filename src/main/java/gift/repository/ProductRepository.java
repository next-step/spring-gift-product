package gift.repository;

import gift.entity.Product;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

    private final NamedParameterJdbcTemplate template;
    private final SimpleJdbcInsert jdbcInsert;

    private final RowMapper<Product> rowMapper = (rs, rowNum) -> new Product(
            rs.getLong("id"),
            rs.getString("name"),
            rs.getLong("price"),
            rs.getString("image_url")
    );

    public ProductRepository(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("product")
                .usingGeneratedKeyColumns("id");
    }

    // 전체 조회
    public List<Product> findAll() {
        String sql = "SELECT id, name, price, image_url FROM product";
        return template.query(sql, rowMapper);
    }

    // 단건 조회
    public Optional<Product> findById(Long id) {
        String sql = "SELECT id, name, price, image_url FROM product WHERE id = :id";
        SqlParameterSource params = new MapSqlParameterSource("id", id);
        List<Product> list = template.query(sql, params, rowMapper);
        return list.isEmpty() ? Optional.empty() : Optional.of(list.get(0));
    }

    // 추가
    public Product save(Product product) {
        String sql = "INSERT INTO product (name, price, image_url) " +
                "VALUES (:name, :price, :image_url)";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", product.getName())
                .addValue("price", product.getPrice())
                .addValue("image_url", product.getImageUrl());

        Number key = jdbcInsert.executeAndReturnKey(params);
        long id = key.longValue();
        product.setId(id);
        return product;
    }

    // 수정
    public Optional<Product> update(Long id, Product product) {
        String sql = "UPDATE product " +
                "SET name = :name, price = :price, image_url = :image_url " +
                "WHERE id = :id";
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("name", product.getName())
                .addValue("price", product.getPrice())
                .addValue("image_url", product.getImageUrl());

        int affected = template.update(sql, params);
        if (affected == 0) {
            return Optional.empty();
        }
        product.setId(id);
        return Optional.of(product);
    }

    // 삭제
    public boolean delete(Long id) {
        String sql = "DELETE FROM product WHERE id = :id";
        int affected = template.update(sql, Collections.singletonMap("id", id));
        return affected > 0;
    }
}