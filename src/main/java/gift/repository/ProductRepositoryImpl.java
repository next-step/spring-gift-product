package gift.repository;

import gift.entity.Product;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final JdbcClient jdbcClient;

    public ProductRepositoryImpl(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Product addProduct(String name, Long price, String url) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("insert into product (name, price, url) values (:name, :price, :url)")
                .param("name", name)
                .param("price", price)
                .param("url", url)
                .update(keyHolder);
        Long id = keyHolder.getKey().longValue();
        return findProductById(id);
    }

    @Override
    public Product findProductById(Long id) {
        Product product = jdbcClient.sql("select id, name, price, url from product where id = :id")
                .param("id", id)
                .query(Product.class)
                .single();
        return product;
    }

    @Override
    public List<Product> findAllProduct() {
        List<Product> products = jdbcClient.sql("select id, name, price, url from product")
                .query(Product.class)
                .list();
        return products;
    }

    @Override
    public void updateProductById(Product newProduct) {
    }

    @Override
    public void deleteProductById(Long id) {
    }
}
