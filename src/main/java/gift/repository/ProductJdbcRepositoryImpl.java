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
    String sql = "select * from products where id=?";
    return jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
            new ProductResponseDto(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getLong("price"),
                rs.getString("imageUrl")
            )
        , id);
  }

  @Override
  public ProductResponseDto createProduct(ProductRequestDto requestDto) {
    String checkSql = "select count(*) from products where id=?";
    int count = jdbcTemplate.queryForObject(checkSql, Integer.class, requestDto.getId());

    if (count != 0) {
      throw new IllegalStateException("이미 존재하는 id");
    }
    String sql = "insert into products (id,name,price,imageUrl) values (?, ?, ?, ?)";
    jdbcTemplate.update(sql, requestDto.getId(), requestDto.getName(), requestDto.getPrice(),
        requestDto.getImageUrl());

    return new ProductResponseDto(requestDto.getId(), requestDto.getName(), requestDto.getPrice(),
        requestDto.getImageUrl());
  }

  @Override
  public ProductResponseDto updateProduct(Long id, ProductRequestDto requestDto) {
    String sql = "update products set name=?, price=?, imageUrl=? where id=?";
    jdbcTemplate.update(sql, requestDto.getName(), requestDto.getPrice(), requestDto.getImageUrl(),
        id);
    return new ProductResponseDto(requestDto.getId(), requestDto.getName(), requestDto.getPrice(),
        requestDto.getImageUrl());
  }

  @Override
  public void deleteProduct(Long id) {

  }
}
