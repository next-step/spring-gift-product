package gift.api.repository;

import gift.api.domain.Product;
import gift.exception.ProductNotFoundException;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final JdbcClient jdbcClient;

    public ProductRepositoryImpl(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Page<Product> findAllProducts(Pageable pageable, Long categoryId) {
        String baseSql = "select * from product";
        String countSql = "select count(*) from product";

        if (categoryId != null) {
            baseSql += " where category_id = ?";
            countSql += " where category_id = ?";
        }

        List<Product> products = jdbcClient.sql(baseSql +
                        " order by " + getSortOrder(pageable.getSort()) +
                        " limit :limit offset :offset")
                .param("limit", pageable.getPageSize())
                .param("offset", pageable.getOffset())
                .query(Product.class)
                .list();

        long total = jdbcClient.sql(countSql).query(Long.class).single();

        return new PageImpl<>(products, pageable, total);
    }

    @Override
    public Product findProductById(Long id) {
        return jdbcClient.sql("select * from product where id = :id")
                .param("id", id)
                .query(Product.class)
                .optional()
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public Product createProduct(Product product) {
        jdbcClient.sql(
                        "insert into product (name, price, image_url) values (:name, :price, :image_url)")
                .param("name", product.getName())
                .param("price", product.getPrice())
                .param("image_url", product.getImageUrl())
                .update();

        Long newId = jdbcClient.sql("select max(id) from product")
                .query(Long.class)
                .single();

        return findProductById(newId);
    }

    @Override
    public Product updateProduct(Product product) {
        int updated = jdbcClient.sql(
                        "update product set name = :name, price = :price, image_url = :image_url where id = :id")
                .param("name", product.getName())
                .param("price", product.getPrice())
                .param("image_url", product.getImageUrl())
                .param("id", product.getId())
                .update();

        if (updated == 0) {
            throw new ProductNotFoundException(product.getId());
        }

        return findProductById(product.getId());
    }

    @Override
    public void deleteProduct(Long id) {
        int deleted = jdbcClient.sql("delete from product where id = :id")
                .param("id", id)
                .update();

        if (deleted == 0) {
            throw new ProductNotFoundException(id);
        }
    }

    private String getSortOrder(Sort sort) {
        if (sort.isSorted()) {
            return "id ASC";
        }

        return sort.stream()
                .map(order -> order.getProperty() + (order.isDescending() ? " DESC" : " ASC"))
                .reduce((a, b) -> a + ", " + b)
                .orElse("id ASC");
    }
}
