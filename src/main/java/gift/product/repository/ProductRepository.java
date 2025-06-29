package gift.product.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import gift.product.entity.Product;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //임시 저장소
    private final Map<Long, Product> products = new ConcurrentHashMap<>();
    private final AtomicLong id = new AtomicLong(1L);

    //단건 조회
    public Optional<Product> findById(Long id) {

        String sql = "select * from product where id = ?";
        List<Product> productList = jdbcTemplate.query(sql ,(rs, rowNum) -> {

            // 추후 refactor
            Product product = new Product();
            product.setId(rs.getLong("id"));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getInt("price"));
            product.setImageUrl(rs.getString("image_url"));
            return product;

        }, id);

        return Optional.ofNullable(productList.getFirst());
    }

    //전체 조회
    public List<Product> findAll() {
        String sql = "select * from product";
        return jdbcTemplate.query(sql ,(rs, rowNum) -> {

            // 추후 refactor
            Product product = new Product();
            product.setId(rs.getLong("id"));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getInt("price"));
            product.setImageUrl(rs.getString("image_url"));
            return product;

        });
    }

    //추가
    public Product save(Product product) {
        Long getId = id.incrementAndGet();
        product.setId(getId);
        products.put(product.getId(), product);
        return products.get(product.getId());
    }

    //수정
    public Product update(Product product) {
        if(products.containsKey(product.getId())) {
            return products.put(product.getId(), product);
        }
        return null;
    }

    //삭제
    public void delete(Long id) {
        products.remove(id);
    }


}
