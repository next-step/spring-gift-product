package gift.product.repository;

import gift.global.common.dto.SortInfo;
import gift.product.domain.Product;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import javax.sql.DataSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcProductRepository implements ProductRepository{
  private final NamedParameterJdbcTemplate jdbcTemplate;
  private final SimpleJdbcInsert jdbcInsert;

  public JdbcProductRepository(DataSource dataSource) {
    this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    this.jdbcInsert = new SimpleJdbcInsert(dataSource)
        .withTableName("product")
        .usingGeneratedKeyColumns("id");
  }

  @Override
  public Long save(Product product) {
    Objects.requireNonNull(product,"상품은 null이 될 수 없습니다.");
    SqlParameterSource params = new BeanPropertySqlParameterSource(product);
    Number key = jdbcInsert.executeAndReturnKey(params);
    return key.longValue();
  }

  @Override
  public Optional<Product> findById(Long id) {
    Objects.requireNonNull(id,"ID은 null이 될 수 없습니다.");
    String sql = "SELECT * FROM Product WHERE id = :id";
    try {
      Map<String, Object> params = Map.of("id", id);
      return Optional.of(jdbcTemplate.queryForObject(sql, params, productRowMapper()));
    } catch (EmptyResultDataAccessException e) {
      return Optional.empty();
    }
  }

  @Override
  public List<Product> findAll(int offset, int pageSize, SortInfo sortInfo) {
    String sql = String.format(
        "SELECT * FROM product ORDER BY %s %s LIMIT :limit OFFSET :offset",
        sortInfo.field(), sortInfo.isAscending()
    );

    MapSqlParameterSource params = new MapSqlParameterSource()
        .addValue("limit", pageSize)
        .addValue("offset", offset);

    return jdbcTemplate.query(sql, params, productRowMapper());
  }

  @Override
  public void update(Long id, Product updateProduct) {
    Objects.requireNonNull(id,"ID는 null일 수 없습니다");
    Objects.requireNonNull(updateProduct,"상품은 null일 수 없습니다");

    String sql = "UPDATE product SET name = :name, price = :price, description = :description, imageUrl =: imageUrl " +
        "WHERE id = :id";

    SqlParameterSource params = new MapSqlParameterSource()
        .addValue("id",id)
        .addValue("name",updateProduct.name())
        .addValue("price",updateProduct.price())
        .addValue("description",updateProduct.description())
        .addValue("imageUrl",updateProduct.imageUrl());

    int affected = jdbcTemplate.update(sql, params);
    if (affected == 0) {
      throw new IllegalArgumentException("수정 실패");
    }
  }

  @Override
  public void deleteById(Long id) {
    Objects.requireNonNull(id,"ID는 null일 수 없습니다");

    String sql = "DELETE FROM schedule WHERE id = :id";
    SqlParameterSource params = new MapSqlParameterSource()
        .addValue("id",id);

    int affected = jdbcTemplate.update(sql, params);
    if (affected == 0) {
      throw new IllegalArgumentException("삭제 실패");
    }
  }

  private RowMapper<Product> productRowMapper(){
    return BeanPropertyRowMapper.newInstance(Product.class);
  }
}
