package gift.repository;

import gift.domain.Product;
import gift.dto.CreateProductRequest;
import gift.dto.UpdateProductRequest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class ProductJdbcRepository implements ProductRepository {

    private final NamedParameterJdbcTemplate template;
    private final SimpleJdbcInsert jdbcInsert;

    public ProductJdbcRepository(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("product")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Product save(CreateProductRequest request) {
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(request);
        Number key = jdbcInsert.executeAndReturnKey(param);
        Product product = new Product(key.longValue(), request.name(), request.price(), request.imageUrl());
        return product;
    }

    @Override
    public Optional<Product> findById(Long id) {
        String sql = "select id, name, price, image_url from product where id = :id";
        Map<String, Long> param = Map.of("id", id);
        Product product = template.queryForObject(sql, param, productRowMapper());
        return Optional.of(product);
    }

    @Override
    public List<Product> findAll() {
        String sql = "select id, name, price, image_url from product";
        return template.query(sql, productRowMapper());
    }

    @Override
    public Product update(Long id, UpdateProductRequest request) {
        String sql = "update product set name=:name, price=:price, image_url=:imageUrl where id = :id";
        MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("name", request.name())
                .addValue("price", request.price())
                .addValue("imageUrl", request.imageUrl())
                .addValue("id", id);
        template.update(sql, param);
        return new Product(id, request.name(), request.price(), request.imageUrl());
    }

    @Override
    public void delete(Long id) {
        String sql = "delete from product where id = :id";
        MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", id);
        template.update(sql, param);
    }

    private RowMapper<Product> productRowMapper() {
        return (rs, rowNum) -> {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            int price = rs.getInt("price");
            String imageUrl = rs.getString("image_url");

            return new Product(id, name, price, imageUrl);
        };
    }



//    private RowMapper<Product> productRowMapper() {
//        return BeanPropertyRowMapper.newInstance(Product.class);
//    }
}
