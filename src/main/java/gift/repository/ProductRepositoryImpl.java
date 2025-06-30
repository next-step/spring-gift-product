package gift.repository;

import gift.dto.PageRequestDto;
import gift.dto.PageResult;
import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import gift.exception.ProductNotFoundException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final JdbcClient jdbcClient;

    public ProductRepositoryImpl(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        var sql = "INSERT INTO product(name, price, image_url) VALUES (:name, :price, :imageUrl);";
        jdbcClient.sql(sql)
                .param("name", productRequestDto.name())
                .param("price", productRequestDto.price())
                .param("imageUrl", productRequestDto.imageUrl())
                .update(keyHolder);

        return new ProductResponseDto(keyHolder.getKey().longValue(), productRequestDto.name(), productRequestDto.price(), productRequestDto.imageUrl());
    }

    @Override
    public PageResult<ProductResponseDto> findAllProducts(PageRequestDto pageRequestDto) {
        int page = Math.max(pageRequestDto.page(), 0);
        int size = pageRequestDto.size();
        int offset = page * size;

        String sql = "SELECT id, name, price, image_url FROM product LIMIT :limit OFFSET :offset";

        List<ProductResponseDto> content = jdbcClient.sql(sql)
                .param("limit", size)
                .param("offset", offset)
                .query(getProductRowMapper())
                .list();

        int total = jdbcClient.sql("SELECT COUNT(*) FROM product")
                .query(Integer.class)
                .single();


        int totalPages = (int) Math.ceil((double) total / size);

        return new PageResult<>(content, page, totalPages, size, total);
    }

    @Override
    public ProductResponseDto findProductById(Long id) {
        String sql = "SELECT id, name, price, image_url FROM product WHERE id = :id";

        return jdbcClient.sql(sql)
                .param("id", id)
                .query(getProductRowMapper())
                .optional()
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto) {
        String sql = "UPDATE product SET name = :name, price = :price, image_url = :imageUrl WHERE id = :id";

        int updated = jdbcClient.sql(sql)
                .param("name", productRequestDto.name())
                .param("price", productRequestDto.price())
                .param("imageUrl", productRequestDto.imageUrl())
                .param("id", id)
                .update();

        if (updated == 0)
            throw new ProductNotFoundException(id);

        return new ProductResponseDto(id, productRequestDto.name(), productRequestDto.price(), productRequestDto.imageUrl());
    }

    @Override
    public void deleteProduct(Long id) {
        String sql = "DELETE FROM product WHERE id = :id";

        int deleted = jdbcClient.sql(sql)
                .param("id", id)
                .update();

        if (deleted == 0)
            throw new ProductNotFoundException(id);
    }

    private ProductResponseDto toResponseDto(Product product) {
        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getImageUrl()
        );
    }

    private static RowMapper<ProductResponseDto> getProductRowMapper() {
        return (rs, rowNum) -> {
            var id = rs.getLong("id");
            var name = rs.getString("name");
            var price = rs.getInt("price");
            var imageUrl = rs.getString("imageUrl");
            return new ProductResponseDto(id, name, price, imageUrl);
        };
    }
}
