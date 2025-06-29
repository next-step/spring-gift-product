package gift.repository;

import gift.dto.ProductRequestDto;
import gift.entity.Product;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    private static final RowMapper<Product> productRowMapper = (rs, rowNum) -> new Product(
        rs.getLong("id"), rs.getString("name"), rs.getInt("price"), rs.getString("imageUrl"));

    public ProductRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Product> findAll() {
        String sql = "select * from product";

        List<Product> productList = jdbcTemplate.query(sql, productRowMapper);
        return productList;
    }

    @Override
    public Long create(ProductRequestDto requestDto) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("product").usingGeneratedKeyColumns("id");

        Map<String, Object> params = new HashMap<>();
        params.put("name", requestDto.name());
        params.put("price", requestDto.price());
        params.put("imageUrl", requestDto.imageUrl());

        Long id = (Long) jdbcInsert.executeAndReturnKey(params);
        return id;
    }

    @Override
    public Product find(Long id) {
        String sql = "select * from product where id = ?";

        Product product = jdbcTemplate.queryForObject(sql, productRowMapper, id);
        return product;
    }

    @Override
    public int update(Long id, ProductRequestDto requestDto) {
        String sql = "update product set name = ?, price = ?, imageUrl = ? where id = ?";

        int updateRowCount = jdbcTemplate.update(
            sql,
            requestDto.name(),
            requestDto.price(),
            requestDto.imageUrl(),
            id
        );

        return updateRowCount;
    }

    @Override
    public void delete(Long id) {
        String sql = "delete from product where id = ?";

        int deleteRowCount = jdbcTemplate.update(sql, id);

        if (deleteRowCount <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
