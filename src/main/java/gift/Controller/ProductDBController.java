package gift.Controller;

import gift.Entity.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import study.Member;

import java.sql.SQLException;
import java.util.List;

@RestController
public class ProductDBController {

    private final JdbcTemplate client;
    private final ProductDao productDao;

    public ProductDBController(JdbcTemplate client, ProductDao productDao) {
        this.client = client;
        this.productDao = productDao;
    }

    @PostMapping("/products")
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        String sql = "INSERT INTO products(name, price, imageUrl) VALUES (?, ?, ?)";
        client.update(sql, product.getName(), product.getPrice(), product.getImageUrl());
        return ResponseEntity.ok("상품 추가 완료");
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> showAllProducts() {
        String sql = "SELECT id, name, price, imageUrl FROM products";
        List<Product> products = client.query(
                sql,
                (resultSet, rowNum) -> new Product(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("price"),
                        resultSet.getString("imageUrl")
                )
        );
        return ResponseEntity.ok().body(products);
    }

    @GetMapping("/products/{id}")
    public Product selectProduct(@PathVariable Long id) throws SQLException {
        return productDao.selectProduct(id);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        String sql = "UPDATE products SET name = ?, price = ?, imageUrl = ? WHERE id = ?";
        client.update(sql, product.getName(), product.getPrice(), product.getImageUrl(), id);
        return ResponseEntity.ok("상품 수정 완료");
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) throws SQLException {
        String sql = "DELETE FROM products WHERE id = ?";
        client.update(sql, id);
        return ResponseEntity.ok("상품 삭제 완료");
    }
}
