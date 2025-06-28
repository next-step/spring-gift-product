package gift.domain.product.repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import gift.common.pagination.Page;
import gift.common.pagination.Pageable;
import gift.common.pagination.PageImpl;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import gift.domain.product.model.Product;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final JdbcClient jdbcClient;

    public ProductRepositoryImpl(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    private static final RowMapper<Product> PRODUCT_MAPPER = (rs, rowNum) -> new Product(
            rs.getLong("id"),
            rs.getString("name"),
            rs.getInt("price"),
            rs.getString("image_url")
    );

    public Page<Product> find(Pageable pageable) {

        int totalRow = jdbcClient.sql("""
                        SELECT COUNT(*)
                        FROM Product;
                """).query(Integer.class).single();

        int start = pageable.getOffset();

        if (start > totalRow) {
            return new PageImpl<>(Collections.emptyList(), pageable, totalRow);
        }

        List<Product> content = jdbcClient.sql("""
                            SELECT id, name, price, image_url
                            FROM PRODUCT
                            LIMIT :limit OFFSET :offset
                        """).param("limit", pageable.getSize())
                .param("offset", start)
                .query(PRODUCT_MAPPER)
                .list();

        return new PageImpl<>(content, pageable, totalRow);
    }

    public Optional<Product> findById(Long id) {

        return id == null ? null : jdbcClient.sql(
                        """
                                SELECT id, name, price, image_url
                                FROM PRODUCT
                                WHERE id = :id
                                """).param("id", id)
                .query(PRODUCT_MAPPER)
                .optional();
    }

    public Product save(Product product) {
//        if (product.getId() != null) {
//            products.put(product.getId(),product);
//        }
//        long id = sequence.incrementAndGet();
//        Product newProduct = new Product(id, product.getName(), product.getPrice(), product.getImageUrl());
//        products.put(id, newProduct);
//        return newProduct;
        return null;
    }

    public void deleteById(Long id) {
//        products.remove(id);
    }

    public boolean existsById(Long id) {
//        return products.containsKey(id);
        return true;
    }
    
}
