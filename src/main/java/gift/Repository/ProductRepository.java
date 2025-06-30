package gift.repository;

import gift.entity.Product;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;


@Repository
public class ProductRepository {

    //private final Map<Long, Product> storage = new LinkedHashMap<>();
    //private final AtomicLong nextId = new AtomicLong(1);

    private final JdbcTemplate jdbcTemplate;
    public ProductRepository(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    private final RowMapper<Product> productRowMapper = (rs, rowNum) -> {
        Product p = new Product();
        p.setId(rs.getLong("id"));
        p.setName(rs.getString("name"));
        p.setPrice(rs.getInt("price"));
        p.setImgUrl(rs.getString("imgUrl"));
        return p;
    };

    /*
    public Product save(Product product) {
        if (product.getId() == null) {
            product.setId(nextId.getAndIncrement());
        }
        storage.put(product.getId(), product);
        return product;
    }
    */
    public Product save(Product product) {
        if(product.getId() == null){
            jdbcTemplate.update("Insert into products (name, price, imgUrl) values (?, ?, ?)", product.getName(), product.getPrice(), product.getImgUrl());
            Long id = jdbcTemplate.queryForObject("select max(id) from products", Long.class);
            product.setId(id);
        }else {
            jdbcTemplate.update("Update products set name =? ,price =? ,imgUrl =? where id =?", product.getName(), product.getPrice(), product.getImgUrl(), product.getId());
        }
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
