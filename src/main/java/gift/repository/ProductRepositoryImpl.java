package gift.repository;

import gift.dto.ProductAddRequestDto;
import gift.entity.Product;
import gift.exception.ProductNotFoundException;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final JdbcClient jdbcClient;

    public ProductRepositoryImpl(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public void addProduct(Product product) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("insert into product (name, price, url) values (:name, :price, :url)")
                .param("name", product.name())
                .param("price", product.price())
                .param("url", product.url())
                .update();
    }

    @Override
    public Product findProductByIdOrElseThrow(Long id) {
        Optional<Product> product = jdbcClient.sql("select id, name, price, url from product where id = :id")
                .param("id", id)
                .query(Product.class)
                .optional();
        return product.orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public List<Product> findAllProduct() {
        List<Product> products = jdbcClient.sql("select id, name, price, url from product")
                .query(Product.class)
                .list();
        return products;
    }

    @Override
    public int updateProductById(Product product) {
        int result = jdbcClient.sql("update product set name = :name, price = :price, url = :url where id = :id")
                .param("name", product.name())
                .param("price", product.price())
                .param("url", product.url())
                .param("id", product.id())
                .update();
        return result;
    }

    @Override
    public void deleteProductById(Long id) {
        jdbcClient.sql("delete from product where id = :id")
                .param("id", id)
                .update();
    }
}
