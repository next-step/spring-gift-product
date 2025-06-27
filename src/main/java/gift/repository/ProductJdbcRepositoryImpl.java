package gift.repository;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductJdbcRepositoryImpl implements ProductRepository {

  private JdbcTemplate jdbcTemplate;

  public ProductJdbcRepositoryImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public List<ProductResponseDto> findAllProduct() {
    String sql = "select * from products";
    return jdbcTemplate.query(sql, (rs, rowNum) ->
        new ProductResponseDto(
            rs.getLong("id"),
            rs.getString("name"),
            rs.getLong("price"),
            rs.getString("imageUrl")
        )
    );
  }

  @Override
  public ProductResponseDto findProductById(Long id) {
    return null;
  }

  @Override
  public ProductResponseDto createProduct(ProductRequestDto requestDto) {
    return null;
  }

  @Override
  public ProductResponseDto updateProduct(Long id, ProductRequestDto requestDto) {
    return null;
  }

  @Override
  public void deleteProduct(Long id) {

  }
}
