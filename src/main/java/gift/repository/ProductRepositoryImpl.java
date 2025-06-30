package gift.repository;

import gift.entity.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final Map<Long, Product> products = new HashMap<>();

    private final JdbcTemplate jdbcTemplate;

    public ProductRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Product> findAllProducts() {

        String sql = "select id, name, price, image_url from products";
        List<Product> allProducts = jdbcTemplate.query(
                sql, (resultSet, rowNum) -> {
                    Product product = new Product(
                            resultSet.getLong("id"),
                            resultSet.getString("name"),
                            resultSet.getLong("price"),
                            resultSet.getString("image_url")
                    );
                    return product;
                });
        return allProducts;
    }

    @Override
    public Product saveProduct(Product product) {
        Long productId = products.isEmpty() ? 1 : Collections.max(products.keySet()) + 1;
        product.setId(productId);
        String sql = "insert into products(id, name, price, image_url) values(?,?,?,?)";
        jdbcTemplate.update(sql, productId, product.getName(), product.getPrice(), product.getImageUrl());

        return product;
    }

    @Override
    public void deleteProduct(Long id) {
        String sql = "delete from products where id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public Product findProduct(Long id) {
        String sql = "select id, name, price, image_url from products where id = ?";
        Product product = jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},  // ← 파라미터 추가
                (resultSet, rowNum) -> new Product(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getLong("price"),
                        resultSet.getString("image_url")
                ));
        return product;
    }

    @Override
    public int updateProduct(Long id, String name, Long price, String imageUrl) {
        String sql = "update products SET name = ?, price = ?, image_url = ? WHERE id = ?";
        int rows = jdbcTemplate.update(sql, name, price, imageUrl, id);
        return rows;
    }
}
