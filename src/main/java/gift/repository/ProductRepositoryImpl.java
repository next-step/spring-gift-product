package gift.repository;

import gift.entity.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final JdbcClient jdbcClient;

    public ProductRepositoryImpl(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    private Long idNum = 1L;

    @Override
    public Product save(Product product) {
        product.setId(idNum++);

        final var sql = "insert into product (id, name, imageUrl) values (?, ?, ?)";
        jdbcClient.sql(sql)
                .params(List.of(
                        product.getId(),
                        product.getName(),
                        product.getImageUrl()
                ))
                .update();

        return product;
    }

    @Override
    public List<Product> findAll() {
        return jdbcClient.sql("select * from product")
                .query((rs, rowNum) -> new Product(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("imageUrl")
                ))
                .list();
    }

    @Override
    public Optional<Product> findById(Long id) {
        var sql = "select id, name, imageUrl from product where id = ?";

        return Optional.ofNullable(jdbcClient.sql(sql)
                .params(List.of(id))
                .query(getProductRowMapper())
                .single()
        );
    }

    @Override
    public void deleteById(Long id) {
        var sql = "delete from product where id = ?";

        jdbcClient.sql(sql)
                .params(List.of(id))
                .update();
    }


    private static RowMapper<Product> getProductRowMapper() {
        return (rs, rowNum) -> {
            final var id = rs.getLong("id");
            final var name = rs.getString("name");
            final var imageUrl = rs.getString("imageUrl");
            return new Product(id, name, imageUrl);
        };
    }
}
