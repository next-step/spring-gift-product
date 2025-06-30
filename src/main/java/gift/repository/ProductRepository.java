package gift.repository;

import gift.domain.Product;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

    private final JdbcClient jdbcClient;

    public ProductRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public Product save(Product product) {
        String sql = """
            INSERT INTO product (name, price, image_url)
            VALUES (?, ?, ?)
            """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcClient.sql(sql)
            .param(product.getName())
            .param(product.getPrice())
            .param(product.getImageURL())
            .update(keyHolder);

        Long id = keyHolder.getKey().longValue();
        product.setId(id);

        return product;
    }

    public Product findById(Long id) {
        String sql = """
            SELECT id, name, price, image_url
            FROM product
            WHERE id = ?
            """;

        return jdbcClient.sql(sql)
            .param(id)
            .query(productRowMapper())
            .single();
    }

    public void delete(Long id) {
        String sql = """
            DELETE FROM product
            WHERE id = ?
            """;

        jdbcClient.sql(sql)
            .param(id)
            .update();
    }

    public int update(Long id, Product product) {
        boolean check = false; // 앞서 수정한 column 값이 존재하는지?
        List<Object> params = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE product SET ");

        if (product.getName() != null) {
            sql.append("name = ?");
            params.add(product.getName());
            check = true;
        }

        if (product.getPrice() != null) {
            if (check) {
                sql.append(", ");
            }

            sql.append("price = ?");
            params.add(product.getPrice());
            check = true;
        }

        if (product.getImageURL() != null) {
            if (check) {
                sql.append(", ");
            }

            sql.append("image_url = ?");
            params.add(product.getImageURL());
        }

        if (params.isEmpty()) {
            return 0;
        }

        sql.append("WHERE id = ?");
        params.add(id);

        return jdbcClient.sql(sql.toString())
            .params(params)
            .update();
    }

    public List<Product> findAll() {
        String sql = """
            SELECT id, name, price, image_url
            FROM product
            """;

        return jdbcClient.sql(sql)
            .query(productRowMapper())
            .list();
    }

    private RowMapper<Product> productRowMapper() {
        return ((rs, rowNum) ->
            new Product(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getInt("price"),
                rs.getString("image_url")
            )
        );
    }
}
