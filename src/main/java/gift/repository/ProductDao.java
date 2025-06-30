package gift.repository;

import gift.model.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDao {
    private final JdbcTemplate jdbcTemplate;

    public ProductDao(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    public void insertProduct(Product product) {
        var sql = "insert into product(id, name, price, image) values(?, ?, ?, ?)";
        jdbcTemplate.update(sql, product.getId(), product.getName(), product.getPrice(), product.getImage());
    }
    public List<Product> getAllProducts() {
        var sql = "select * from product";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Product(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getInt("price"),
                rs.getString("image")
        ));
    }
    public Product getProductById(long id) {
        var sql = "select * from product where id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new Product(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getInt("price"),
                rs.getString("image")
        ), id);
    }
    public void removeProduct(long id) {
        var sql = "delete from product where id = ?";
        jdbcTemplate.update(sql, id);
    }
    public void updateProduct(Long id, Product product_origin, Product product) {
        var sql = "UPDATE product SET name = ?, price = ?, image = ? WHERE id = ?";
        if (product.getId() == null){
            product.setId(product_origin.getId());
        }
        if (product.getName() == null){
            product.setName(product_origin.getName());
        }
        if (product.getPrice() == null){
            product.setPrice(product_origin.getPrice());
        }
        if (product.getImage() == null){
            product.setImage(product_origin.getImage());
        }
        jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getImage(),id);
    }
}