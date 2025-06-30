package gift.repository;

import gift.model.Product;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

  private final JdbcTemplate jdbcTemplate;
  private final SimpleJdbcInsert insert;

  public ProductRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
    this.insert = new SimpleJdbcInsert(jdbcTemplate)
        .withTableName("product")
        .usingGeneratedKeyColumns("id");
  }

  public List<Product> findAll() {
    String sql = "select * from product";
    return jdbcTemplate.query(sql, this::mapRow);
  }

  public Optional<Product> findById(Long id) {
    String sql = "select * from product where id = ?";
    return jdbcTemplate.query(sql, this::mapRow, id)
        .stream()
        .findFirst();
  }

  public Product save(Product product) {
    Map<String, Object> params = new HashMap<>();
    params.put("name", product.getName());
    params.put("price", product.getPrice());
    params.put("imageUrl", product.getImageUrl());

    Number key = insert.executeAndReturnKey(params);
    product.setId(key.longValue());
    return product;
  }

  public void update(Product updateProduct) {
    String sql = "update product set name = ?, price = ?, imageurl = ? where id = ?";
    jdbcTemplate.update(sql, updateProduct.getName(), updateProduct.getPrice(),
        updateProduct.getImageUrl(), updateProduct.getId());
  }

  public void delete(Long id) {
    String sql = "delete from product where id = ?";
    jdbcTemplate.update(sql, id);
  }

  private Product mapRow(ResultSet rs, int rowNum) throws SQLException {
    return new Product(
        rs.getLong("id"),
        rs.getString("name"),
        rs.getInt("price"),
        rs.getString("imageUrl")
    );
  }
}
