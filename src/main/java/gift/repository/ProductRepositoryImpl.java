package gift.repository;

import gift.dto.ProductAddRequestDto;
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
    public void addProduct(ProductAddRequestDto requestDto) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("insert into product (name, price, url) values (:name, :price, :url)")
                .param("name", requestDto.name())
                .param("price", requestDto.price())
                .param("url", requestDto.url())
                .update();
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
    public void updateProductById(Product product) {
        jdbcClient.sql("update product set name = :name, price = :price, url = :url where id = :id")
                .param("name", product.name())
                .param("price", product.price())
                .param("url", product.url())
                .param("id", product.id())
                .update();
    }

    @Override
    public void deleteProductById(Long id) {
        jdbcClient.sql("delete from product where id = :id")
                .param("id", id)
                .update();
    }
}
