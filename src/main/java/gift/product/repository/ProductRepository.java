package gift.product.repository;

import gift.product.dto.ProductResponseDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.RowMapper;
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

    private final RowMapper<Product> productRowMapper = (resultSet, rowNum) -> {
        Product product = new Product();
        product.setId(resultSet.getLong("id"));
        product.setName(resultSet.getString("name"));
        product.setPrice(resultSet.getInt("price"));
        product.setImageUrl(resultSet.getString("image_url"));
        return product;
    };

    //단건 조회
    public Optional<Product> findById(Long id) {

        String sql = "select * from product where id = ?";
        List<Product> productList = jdbcTemplate.query(sql ,productRowMapper, id);

        return Optional.ofNullable(productList.getFirst());
    }

    //전체 조회
    public List<Product> findAll() {
        String sql = "select * from product";
        return jdbcTemplate.query(sql ,productRowMapper);
    }

    //추가
    public ProductResponseDto save(Product product) {

        String sql = "insert into product(name, price, image_url) values(?, ?, ?)";
        jdbcTemplate.update(sql, productRowMapper);

        return ProductResponseDto.fromEntity(product);
    }

    //수정
    public Product update(Product product) {

        String sql = "update product set name = ?, price = ?, image_url = ? where id = ?";

        jdbcTemplate.update(sql, productRowMapper);

        return product;
    }

    //삭제
    public void delete(Long id) {
        String sql = "delete from product where id = ?";
        jdbcTemplate.update(sql,id);
    }


}
