package gift.repository;

import gift.dto.response.ProductGetResponseDto;
import gift.entity.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProductRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int saveProduct(Product product) {

        String sql = "INSERT INTO products(name, price, imageUrl) VALUES(?,?,?)";

        return jdbcTemplate.update(sql, product.getName(), product.getPrice(),
            product.getImageUrl());
    }

    @Override
    public List<ProductGetResponseDto> findAllProducts() {

        String sql = "SELECT productId, name, price, imageUrl FROM products";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
            new ProductGetResponseDto(
                rs.getLong("productId"),
                rs.getString("name"),
                rs.getDouble("price"),
                rs.getString("imageUrl")
            ));
    }

    @Override
    public Optional<Product> findProductById(Long productId) {
        String sql = "SELECT productId, name, price, imageUrl FROM products WHERE productId = ?";

        try {
            Product product = jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
                    new Product(
                        rs.getLong("productId"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getString("imageUrl")
                    ),
                productId
            );
            return Optional.of(product);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                "ProductRepositoryImpl.findProductById");
        }
    }

    @Override
    public int updateProductById(Long productId, String name, Double price,
        String imageUrl) {

        String sql = "UPDATE products SET name = ?, price = ?, imageUrl = ? WHERE productId = ?";
        return jdbcTemplate.update(sql, name, price, imageUrl, productId);
    }


    @Override
    public int deleteProductById(Long productId) {

        String sql = "DELETE FROM products WHERE productId = ?";
        return jdbcTemplate.update(sql, productId);
    }
}
