package gift.product.repository;

import gift.product.dto.ProductCreateRequestDto;
import gift.product.dto.ProductUpdateRequestDto;
import gift.product.entity.Product;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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

    public List<Product> findAll() {
        return jdbcClient.sql("SELECT * FROM product")
                .query(Product.class).list();
    }

    public Optional<Product> findById(Long id) {
        return jdbcClient.sql("SELECT * FROM product WHERE id=?").param(id)
                .query(Product.class).optional();
    }

    public Long save(ProductCreateRequestDto requestDto) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("INSERT INTO product (name, price, image_url) VALUES (?,?,?)")
                .param(requestDto.name())
                .param(requestDto.price())
                .param(requestDto.imageUrl())
                .update(keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public void update(Long id, ProductUpdateRequestDto requestDto) {
        jdbcClient.sql(
                        "UPDATE product SET name=?, price=?, image_url=? WHERE id=?")
                .param(requestDto.name())
                .param(requestDto.price())
                .param(requestDto.imageUrl())
                .param(id)
                .update();
    }

    public void delete(Long id) {
        jdbcClient.sql("DELETE FROM product WHERE id=?").param(id).update();
    }
}
