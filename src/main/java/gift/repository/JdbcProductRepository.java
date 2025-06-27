package gift.repository;

import gift.domain.Product;
import gift.dto.common.Page;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcProductRepository implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    private static final int MIN_PAGE_NUMBER = 1;
    private static final int MIN_PAGE_SIZE = 1;

    public JdbcProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Product save(Product product) {
        Objects.requireNonNull(product, "상품은 null일 수 없습니다.");

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO product (name, price, image_url) VALUES (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, product.name());
            ps.setInt(2, product.price());
            ps.setString(3, product.imageUrl());
            return ps;
        }, keyHolder);

        Long generatedId = Optional.of(keyHolder.getKey())
                .map(Number::longValue)
                .orElseThrow(() -> new IllegalStateException("ID 생성에 실패했습니다."));

        return new Product(generatedId, product.name(), product.price(), product.imageUrl());
    }

    @Override
    public void update(Long id, Product updatedProduct) {
        Objects.requireNonNull(id, "id는 null일 수 없습니다.");
        Objects.requireNonNull(updatedProduct, "updatedProduct는 null일 수 없습니다.");

        int updatedRows = jdbcTemplate.update(
                "UPDATE product SET name = ?, price = ?, image_url = ? WHERE id = ?",
                updatedProduct.name(),
                updatedProduct.price(),
                updatedProduct.imageUrl(),
                id
        );

        if (updatedRows == 0) {
            throw new IllegalArgumentException("해당 ID에 대한 상품이 존재하지 않아 업데이트할 수 없습니다: " + id);
        }
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update("DELETE FROM product");
    }

    @Override
    public void deleteAllByIds(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return;
        }
        String inSql = String.join(",", Collections.nCopies(ids.size(), "?"));
        jdbcTemplate.update("DELETE FROM product WHERE id IN (" + inSql + ")", ids.toArray());
    }

    @Override
    public void deleteById(Long id) {
        Objects.requireNonNull(id, "id는 null일 수 없습니다.");
        jdbcTemplate.update("DELETE FROM product WHERE id = ?", id);
    }

    @Override
    public List<Product> findAll() {
        return jdbcTemplate.query(
                "SELECT id, name, price, image_url FROM product ORDER BY id",
                this::mapRowToProduct
        );
    }

    @Override
    public Page<Product> findAllByPage(int pageNumber, int pageSize) {
        validatePageParams(pageNumber, pageSize);

        int total = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM product", Integer.class);
        int totalPages = calculateTotalPages(total, pageSize);

        if (pageNumber > totalPages) {
            return new Page<>(Collections.emptyList(), pageNumber, pageSize, total);
        }

        int offset = (pageNumber - 1) * pageSize;

        List<Product> content = jdbcTemplate.query(
                "SELECT id, name, price, image_url FROM product ORDER BY id LIMIT ? OFFSET ?",
                this::mapRowToProduct,
                pageSize,
                offset
        );

        return new Page<>(content, pageNumber, pageSize, total);
    }

    @Override
    public Optional<Product> findById(Long id) {
        if (id == null) {
            return Optional.empty();
        }

        try {
            Product product = jdbcTemplate.queryForObject(
                    "SELECT id, name, price, image_url FROM product WHERE id = ?",
                    this::mapRowToProduct,
                    id
            );
            return Optional.of(product);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    private Product mapRowToProduct(ResultSet rs, int rowNum) throws java.sql.SQLException {
        return Product.withId(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getInt("price"),
                rs.getString("image_url")
        );
    }

    private int calculateTotalPages(int totalItems, int pageSize) {
        return (int) Math.ceil((double) totalItems / pageSize);
    }

    private void validatePageParams(int pageNumber, int pageSize) {
        if (pageNumber < MIN_PAGE_NUMBER) {
            throw new IllegalArgumentException("pageNumber는 1 이상이어야 합니다.");
        }
        if (pageSize < MIN_PAGE_SIZE) {
            throw new IllegalArgumentException("pageSize는 1 이상이어야 합니다.");
        }
    }
}
