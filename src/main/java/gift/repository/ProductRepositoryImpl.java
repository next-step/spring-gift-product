package gift.repository;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.boot.autoconfigure.task.TaskExecutionProperties.Simple;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProductRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ProductResponseDto> findAll() {
        String sql = "select * from product";

        List<ProductResponseDto> productList = jdbcTemplate.query(sql, productRowMapper());
        return productList;
    }

    private RowMapper<ProductResponseDto> productRowMapper() {
        return (rs, rowNum) -> {
            var id = rs.getLong("id");
            var name = rs.getString("name");
            var price = rs.getInt("price");
            var imageUrl = rs.getString("imageUrl");

            return new ProductResponseDto(id, name, price, imageUrl);
        };
    }

    @Override
    public ProductResponseDto createProduct(ProductRequestDto requestDto) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("product").usingGeneratedKeyColumns("id");

        Map<String, Object> params = new HashMap<>();
        params.put("name", requestDto.name());
        params.put("price", requestDto.price());
        params.put("imageUrl", requestDto.imageUrl());

        Long id = (Long) jdbcInsert.executeAndReturnKey(params);
        return new ProductResponseDto(id, requestDto.name(), requestDto.price(), requestDto.imageUrl());
    }

    @Override
    public ProductResponseDto findProduct(Long id) {
        String sql = "select * from product where id = ?";

        ProductResponseDto productResponseDto = jdbcTemplate.queryForObject(sql, productRowMapper(), id);
        return productResponseDto;
    }

    @Override
    public ProductResponseDto updateProduct(Long id, ProductRequestDto requestDto) {
        String sql = "update product set name = ?, price = ?, imageUrl = ? where id = ?";

        jdbcTemplate.update(
            sql,
            requestDto.name(),
            requestDto.price(),
            requestDto.imageUrl(),
            id
        );

        return new ProductResponseDto(id, requestDto.name(), requestDto.price(), requestDto.imageUrl());
    }

    @Override
    public void deleteProduct(Long id) {
        String sql = "delete from product where id = ?";

        int deleteRowCount = jdbcTemplate.update(sql, id);

        if (deleteRowCount <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
