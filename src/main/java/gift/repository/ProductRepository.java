package gift.repository;

import gift.entity.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insert;
    public ProductRepository(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
        this.insert = new SimpleJdbcInsert(jdbcTemplate)
                .usingGeneratedKeyColumns("id")
                .withTableName("products");
    }

    //상품 추가하기
    public Product save(Product product)
    {
        Map<String, Object> map = new HashMap<>();
        map.put("name", product.getName());
        map.put("price", product.getPrice());
        map.put("image_url", product.getImageUrl());
        Number key= insert.executeAndReturnKey(new MapSqlParameterSource(map));
        product.setId(key.longValue());
        return product;
    }

    //조회하기 (전체, id)
    public List<Product> findAll()
    {
        String sql = "select * from products";
        return jdbcTemplate.query(sql,rowMapper());
    }

    public Optional<Product> findById(Long id)
    {
        String sql = "select * from products where id=?";
        List<Product>res=jdbcTemplate.query(sql,rowMapper(),id);
        return res.stream().findFirst();
    }



    private RowMapper<Product> rowMapper()
    {
        return (rs, rowNum) -> new Product(
          rs.getLong("id"),
          rs.getString("name"),
          rs.getInt("price"),
          rs.getString("image_url")
        );
    }
}



