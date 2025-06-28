package gift.repository;

import gift.domain.Product;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public class ProductRepositoryImpl implements ProductRepository {

    private final JdbcClient jdbcClient;

    public ProductRepositoryImpl(JdbcClient jdbcClient){
        this.jdbcClient = jdbcClient;
    }

    @Override
    public void register(Product product){
        jdbcClient.sql("""
                insert into prouct (name, price, image_url)
                values (:name, :price. :imageUrl)
                """)
                .param("name", product.getName())
                .param("price", product.getPrice())
                .param("imageUrl", product.getImageUrl())
                .update();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return jdbcClient.sql("select * from product where id = :id")
                .param("id", id)
                .query(Product.class)
                .optional();
    }

    @Override
    public List<Product> findAll(){
        return jdbcClient.sql("select * from product")
                .query(Product.class)
                .list();
    }

    @Override
    public void update(Long id, Product product){
        jdbcClient.sql("""
                update product
                set name = :name, price = :price, image_url = :imageUrl
                where id = :id
                """)
                .param("id", id)
                .param("name", product.getName())
                .param("price", product.getPrice())
                .param("imageUrl", product.getImageUrl())
                .update();
    }

    @Override
    public void delete(Long id){
        jdbcClient.sql("delete from product where id = :id")
                .param("id",id)
                .update();
    }

    @Override
    public List<Product> searchByName(String keyword){
        return jdbcClient.sql("select * from product where lower(name) like lower(:keyword)")
                .param("keyword", "%" + keyword + "%")
                .query(Product.class)
                .list();
    }
}