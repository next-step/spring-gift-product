package gift.service;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import gift.exception.ProductNotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DefaultProductService implements ProductService {

    private final JdbcTemplate jdbcTemplate;

    public DefaultProductService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 상품 생성
    @Override
    public ProductResponseDto addProduct(ProductRequestDto requestDto) {

        String sql = "INSERT INTO product (name, price, image_url) VALUES (?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[] {"id"});
            ps.setString(1, requestDto.name());
            ps.setInt(2, requestDto.price());
            ps.setString(3, requestDto.imageUrl());
            return ps;
        }, keyHolder);

        Long id = keyHolder.getKey().longValue();

        return new ProductResponseDto(new Product(id, requestDto.name(), requestDto.price(), requestDto.imageUrl()));
    }

    @Override
    public ProductResponseDto getProductById(Long id) {

        String sql = "SELECT * FROM product WHERE id = ?";

        // jdbc에는 query(), queryForObject() 두 가지 존재 (queryForObject()는 예외 처리 필요)
        List<Product> results = jdbcTemplate.query(sql, new Object[]{id}, productRowMapper());

        if (results.isEmpty()) {
            throw new ProductNotFoundException(id);
        }

        return new ProductResponseDto(results.get(0));
    }

    @Override
    public ProductResponseDto updateProduct(Long id, ProductRequestDto requestDto) {

        String sql = "UPDATE product SET name = ?, price = ?, image_url = ? WHERE id = ?";

        int updateCount = jdbcTemplate.update(sql, requestDto.name(), requestDto.price(), requestDto.imageUrl(), id);

        if (updateCount == 0) {
            throw new ProductNotFoundException(id); // 예외 처리
        }

        return getProductById(id);
    }

    @Override
    public ProductResponseDto deleteProduct(Long id) {

        ProductResponseDto existProduct = getProductById(id);

        String sql = "DELETE FROM product WHERE id = ?";
        jdbcTemplate.update(sql, id);

        return existProduct;
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        String sql = "SELECT * FROM product";
        return jdbcTemplate.query(sql, productRowMapper()).stream()
                .map(ProductResponseDto::new)
                .collect(Collectors.toList());
    }

    private RowMapper<Product> productRowMapper() {
        return (rs, rowNum) -> new Product(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getInt("price"),
                rs.getString("image_url")
        );
    }
}
