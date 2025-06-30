package gift.repository;

import gift.domain.Product;
import gift.dto.ProductResponseDto;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcTemplateProductRepository implements ProductRepository {

  private final JdbcTemplate jdbcTemplate;

  public JdbcTemplateProductRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public ProductResponseDto createProduct(Product product) {
    String sql = "INSERT INTO product (name, price, image_url) VALUES (?, ?, ?)";
    KeyHolder keyHolder = new GeneratedKeyHolder();

    jdbcTemplate.update(con -> {
      PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      ps.setString(1, product.getName());
      ps.setInt(2, product.getPrice());
      ps.setString(3, product.getImageUrl());
      return ps;
    }, keyHolder);

    Long id = keyHolder.getKey().longValue();
    return new ProductResponseDto(id, product.getName(), product.getPrice(), product.getImageUrl());
  }

  @Override
  public List<ProductResponseDto> searchAllProducts() {
    String sql = "SELECT * FROM product";
    return jdbcTemplate.query(sql, productResponseDtoRowMapper());
  }

  @Override
  public Optional<Product> searchProductById(Long id) {
    String sql = "SELECT * FROM product WHERE id = ?";
    try {
      Product product = jdbcTemplate.queryForObject(sql, productRowMapper(), id);
      return Optional.ofNullable(product);
    } catch (EmptyResultDataAccessException e) {
      return Optional.empty();
    }
  }

  @Override
  public Product updateProduct(Long id, String name, Integer price, String imageUrl) {
    String sql = "UPDATE product SET name = ?, price = ?, image_url = ? WHERE id = ?";
    int affected = jdbcTemplate.update(sql, name, price, imageUrl, id);
    if (affected == 0) {
      throw new IllegalArgumentException("해당 ID = " + id + " 의 상품이 존재하지 않습니다.");
    }
    return searchProductById(id).get();
  }

  @Override
  public void deleteProduct(Long id) {
    String sql = "DELETE FROM product WHERE id = ?";
    jdbcTemplate.update(sql, id);
  }

  private RowMapper<ProductResponseDto> productResponseDtoRowMapper() {
    return (rs, rowNum) -> new ProductResponseDto(
        rs.getLong("id"),
        rs.getString("name"),
        rs.getInt("price"),
        rs.getString("image_url")
    );
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
