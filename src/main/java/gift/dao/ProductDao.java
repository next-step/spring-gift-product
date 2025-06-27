package gift.dao;

import gift.entity.Product;
import org.springframework.jdbc.core.JdbcTemplate;

public class ProductDao {

    private JdbcTemplate jdbcTemplate;

    public void insert(Product product) {
        String sql = "INSERT INTO product(name, price, image_url) VALUES(?,?,?)";
        jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getImageUrl());
    }

}
