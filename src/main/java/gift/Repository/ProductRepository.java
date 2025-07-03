package gift.repository;

import gift.entity.Product;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    private static final RowMapper<Product> productRowMapper = (rs, rowNum) -> {
        Product p = new Product();
        p.setId(rs.getLong("id"));
        p.setName(rs.getString("name"));
        p.setPrice(rs.getBigDecimal("price"));
        p.setImgUrl(rs.getString("imgUrl"));
        return p;
    };

    /*
    public Product save(Product product) {
        if(product.getId() == null){
            jdbcTemplate.update("Insert into products (name, price, imgUrl) values (?, ?, ?)",
            product.getName(),
            product.getPrice(),
            product.getImgUrl());

            Long id = jdbcTemplate.queryForObject("select max(id) from products", Long.class);
            product.setId(id);
        } else {
            jdbcTemplate.update("Update products set name =? ,price =? ,imgUrl =? where id =?",
            product.getName(),
            product.getPrice(),
            product.getImgUrl(),
            product.getId());
        }
        return product;
    }
    */

    public Product create(Product product) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO products (name, price, imgUrl) VALUES (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, product.getName());
            ps.setBigDecimal(2, product.getPrice());
            ps.setString(3, product.getImgUrl());
            return ps;
        }, keyHolder);

        Long id = keyHolder.getKey().longValue();
        product.setId(id);
        return product;
    }

    public Product update(Product product) {
        jdbcTemplate.update(
                "UPDATE products SET name = ?, price = ?, imgUrl = ? WHERE id = ?",
                product.getName(),
                product.getPrice(),
                product.getImgUrl(),
                product.getId()
        );
        return product;
    }

    public Optional<Product> findById(Long id) {
        List<Product> result = jdbcTemplate.query("select * from products where id = ?", productRowMapper, id);
        return result.stream().findFirst();
    }

    public List<Product> findAll() {
        return jdbcTemplate.query("select * from products", productRowMapper);
    }

    public void deleteById(Long id) {
        jdbcTemplate.update("delete from products where id = ?", id);
    }

    public boolean existsById(Long id) {
        return jdbcTemplate.queryForObject("select count(*) from products where id = ?", Long.class, id) > 0;
    }
}
