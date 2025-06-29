package gift.product.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import gift.product.entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
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
        String sql = "insert into product(name, price, image_url) values(?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, product.getName());
                ps.setInt(2, product.getPrice());
                ps.setString(3, product.getImageUrl());
                return ps;
            }
        }, keyHolder);

        Number key = keyHolder.getKey();
        if(key != null) {
            product.setId(key.longValue());
        }else{
            throw new IllegalStateException("id 값을 불러오지 못했습니다");
        }

        return product;
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
