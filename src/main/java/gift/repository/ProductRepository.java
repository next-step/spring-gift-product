package gift.repository;

import gift.entity.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public class ProductRepository {
    private final JdbcClient jdbcClient;

    private final Map<Long, Product> products = new HashMap<>();
    private long sequence = 0L;

    public ProductRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }


    public Product save(Product product){
        long newId = ++sequence;

        Product newProduct = product.withId(newId);
        products.put(newId, newProduct);
        return newProduct;
    }

    public Optional<Product> findById(Long id){
        return Optional.ofNullable(products.get(id));
    }

    public List<Product> findAll(){
        return new ArrayList<>(products.values());
    }

    public void deleteById(Long id){
        products.remove(id);
    }

    private static RowMapper<Product> getProductRowMapper(){
        return (rs, rowNum) -> new Product(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getLong("price"),
                rs.getString("image_url")
        );
    }

}
