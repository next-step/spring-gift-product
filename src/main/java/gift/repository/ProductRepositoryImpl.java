package gift.repository;

import gift.entity.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final Map<Long, Product> products = new HashMap<>();

    private final JdbcTemplate jdbcTemplate;

    public ProductRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Product> findAllProducts() {

        List<Product> allProducts = new ArrayList<>();
        allProducts.addAll(products.values());
        return allProducts;
    }

    @Override
    public Product saveProduct(Product product) {
        Long productId = products.isEmpty() ? 1 : Collections.max(products.keySet()) + 1;
        product.setId(productId);
        String sql = "insert into product(id, name, price, image_url) values(?,?,?,?)";
        jdbcTemplate.update(sql, productId, product.getName(), product.getPrice(), product.getImageUrl());

        return product;
    }

    @Override
    public void deleteProduct(Long id) {
        products.remove(id);
    }

    @Override
    public Product findProduct(Long id) {
        return products.get(id);
    }
}
