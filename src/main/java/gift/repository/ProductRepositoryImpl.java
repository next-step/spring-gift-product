package gift.repository;

import gift.dto.ProductResponseDto;
import gift.entity.Product;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.util.*;

@Repository
public class ProductRepositoryImpl implements ProductRepository{

    private final JdbcClient jdbcClient;
    private final SimpleJdbcInsert jdbcInsert;

    public ProductRepositoryImpl(DataSource dataSource) {
        this.jdbcClient = JdbcClient.create(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("products")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public List<ProductResponseDto> findAllProducts() {

        String sql = "select * from products";
        List<ProductResponseDto> result = jdbcClient.sql(sql)
                .query(ProductResponseDto.class)
                .list();

        return result;
    }

    @Override
    public Optional<Product> findProductById (Long id) {

        String sql = "select * from products where id = ?";
        Optional<Product> result = jdbcClient.sql(sql)
                .param(id)
                .query(Product.class)
                .optional();

        return result;
    }

    @Override
    public Product findProductByIdElseThrow(Long id) {

        String sql = "select * from products where id = ?";
        Product result = jdbcClient.sql(sql)
                .param(id)
                .query(Product.class)
                .optional()
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "해당 ID의 상품은 존재하지 않습니다."
                        )
                );

        return result;
    }

    @Override
    public Product saveProduct(String name, Long price, String imageUrl) {

        Map<String, Object> params = new HashMap<>();

        params.put("name", name);
        params.put("price", price);
        params.put("imageUrl", imageUrl);

        Number key = jdbcInsert.executeAndReturnKey(params);
        Long id = key.longValue();

        return new Product(id, name, price, imageUrl);
    }

    @Override
    public Product updateProduct(Long id, String name, Long price, String imageUrl) {

        String sql = "update products set name = :name, price = :price, imageUrl = :imageUrl" +
                " where id = :id";

        int colNum = jdbcClient.sql(sql)
                .param("name", name)
                .param("price", price)
                .param("imageUrl", imageUrl)
                .param("id", id)
                .update();

        if (colNum == 0) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "해당 ID의 상품은 존재하지 않습니다."
            );
        }

        return new Product(id, name, price, imageUrl);
    }

    @Override
    public void deleteProduct(Long id) {
        String sql = "delete from products where id = ?";

        jdbcClient.sql(sql)
                .param(id)
                .update();
    }
}
