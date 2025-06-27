package gift.repository;

import gift.entity.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Primary
@Repository
public class DatabaseProductRepository implements ProductRepository {

    private final JdbcClient jdbcClient;

    private final String SAVE_PRODUCT = """
                INSERT INTO products(name, price, imageUrl)
                VALUES (:name, :price, :imageUrl);
        """;
    private final String SELECT_PRODUCT_BY_ID = """
                SELECT id, name, price, imageUrl
                FROM products
                WHERE id = :id;
            """;
    private final String DELETE_PRODUCT_BY_ID = """
                DELETE FROM products
                WHERE id = :id;
            """;
    private final String UPDATE_PRODUCT = """
                UPDATE products
                SET name = :name, price = :price, imageUrl = :imageUrl
                WHERE id = :id;
            """;
    private final String SELECT_ALL_PRODUCTS = """
                SELECT id, name, price, imageUrl
                FROM products;
            """;

    DatabaseProductRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Long saveProduct(String name, Integer price, String imageUrl) {
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcClient.sql(SAVE_PRODUCT)
                .param("name", name)
                .param("price", price)
                .param("imageUrl", imageUrl).
                update(generatedKeyHolder);
        return generatedKeyHolder.getKey().longValue();
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        return jdbcClient.sql(SELECT_PRODUCT_BY_ID)
                .param("id", id)
                .query((rs, rowNum) -> new Product(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        rs.getString("imageUrl"))).optional();
    }

    @Override
    public void deleteProductById(Long id) {
        jdbcClient.sql(DELETE_PRODUCT_BY_ID)
                .param("id", id)
                .update();
    }

    @Override
    public boolean updateProduct(Product product) {
        int numOfUpdatedRows = jdbcClient.sql(UPDATE_PRODUCT)
                .param("id", product.id())
                .param("name", product.name())
                .param("price", product.price())
                .param("imageUrl", product.imageUrl())
                .update();
        return numOfUpdatedRows > 0;
    }

    @Override
    public List<Product> findAllProducts() {
        return jdbcClient.sql(SELECT_ALL_PRODUCTS)
                .query((rs, rowNum) -> new Product(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        rs.getString("imageUrl")
                )).stream().toList();
    }
}
