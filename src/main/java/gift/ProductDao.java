package gift;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class ProductDao {
    private final JdbcTemplate jdbcTemplate;

    public ProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(ProductDTO productDto) {
        String sql = "INSERT INTO PRODUCTS (name, price, imageUrl) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, productDto.getName(), productDto.getPrice(), productDto.getImageUrl());
    }


    public List<Product> getAll() {
        String sql = "SELECT * FROM PRODUCTS";
        return jdbcTemplate.query(sql, productRowMapper());

    }

    public Product findById(Long id) {
        String sql = "SELECT * FROM PRODUCTS WHERE id = ?";
        List<Product> result = jdbcTemplate.query(sql, productRowMapper(), id);
        if(result.isEmpty()) {
            throw new NoSuchElementException("해당 데이터가 없습니다.");
        }
        return result.getFirst();
    }

    public void update(Long id, ProductDTO productDto) {
        String sql = "UPDATE PRODUCTS SET ";
        List<Object> params = new ArrayList<>();
        boolean bool = false;

        if(productDto.getName() != null) {
            sql += "name = ?";
            params.add(productDto.getName());
            bool = true;
        }
        if(productDto.getPrice() != 0) {
            if(bool) {
                sql += ", ";
            }
            sql += "price = ?";
            params.add(productDto.getPrice());
            bool = true;
        }
        if(productDto.getImageUrl() != null) {
            if(bool) {
                sql += ", ";
            }
            sql += "imageUrl = ?";
            params.add(productDto.getImageUrl());
        }
        if(params.isEmpty()) {
            throw new NoSuchElementException("업데이트 필요 없음");
        }
        sql += " WHERE id = ?";
        params.add(id);

        jdbcTemplate.update(sql, params.toArray());
    }

    public void delete(Long id) {
        String sql = "DELETE FROM PRODUCTS WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private RowMapper<Product> productRowMapper() {
        return new RowMapper<Product>() {
            public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                String imageUrl = rs.getString("imageUrl");
                return new Product(id,name,price,imageUrl);
            }
        };
    }
}
