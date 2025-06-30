package gift.service;

import gift.entity.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.util.*;

@Service
public class ProductService {

    private final Map<Long, Product> products = new HashMap<>();

    private JdbcTemplate jdbcTemplate;

    public ProductService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Product> getAllProducts() {
        String sql = "select * from products";
        List<Product> products = jdbcTemplate.query(sql, productRowMapper());
        return products;
    }

    public Optional<Product> getProductById(Long id) {
        String sql = "select * from products where id = ?";
        return jdbcTemplate.query(sql, productRowMapper(), id).stream().findAny();
    }

    public Product createProduct(Product productWithoutId) {
        String sql = "insert into products(name, price, imageUrl) values (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, productWithoutId.getName());
            ps.setInt(2, productWithoutId.getPrice());
            ps.setString(3, productWithoutId.getImageUrl());
            return ps;
        }, keyHolder);

        productWithoutId.setId(keyHolder.getKey().longValue());
        return productWithoutId;
    }

    public Product updateProduct(Long id, Product updateRequest) {
        Product product = products.get(id);

        if (product == null) {
            return null;
        }

        product.setName(updateRequest.getName() != null ? updateRequest.getName() : product.getName());
        product.setPrice(updateRequest.getPrice() != 0 ? updateRequest.getPrice() : product.getPrice());
        product.setImageUrl(updateRequest.getImageUrl() != null ? updateRequest.getImageUrl() : product.getImageUrl());

        return product;
    }

    public boolean deleteProduct(Long id) {
        return products.remove(id) != null;
    }

    private RowMapper<Product> productRowMapper() {
        return (rs, rowNum) -> {
            Product product = new Product(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getInt("price"),
                    rs.getString("imageUrl")
            );
            return product;
        };
    }
}
