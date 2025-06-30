package gift.product.repository;

import gift.product.dto.ProductResponseDto;
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
    public ProductResponseDto save(Product product) {
        String sql = "insert into product(name, price, image_url) values(?, ?, ?)";
        jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getImageUrl());

        return ProductResponseDto.fromEntity(product);
    }

    //수정
    public Product update(Product product) {

        String sql = "update product set name = ?, price = ?, image_url = ? where id = ?";

        jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getImageUrl(), product.getId());

        return product;
    }

    //삭제
    public void delete(Long id) {
        String sql = "delete from product where id = ?";
        jdbcTemplate.update(sql,id);
    }


}
