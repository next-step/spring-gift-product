package gift.repository;

import gift.entity.Item;
import java.util.List;
import java.util.Optional;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepository {

    private final JdbcTemplate jdbcTemplate;

    public ItemRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Item> itemRowMapper = (rs, rowNum) -> new Item(
        rs.getLong("id"),
        rs.getString("name"),
        rs.getInt("price"),
        rs.getString("image_url")
    );

    public Optional<Item> findById(Long id) {
        String sql = "SELECT id, name, price, image_url FROM products WHERE id = ?";
        try {
            Item item = jdbcTemplate.queryForObject(sql, itemRowMapper, id);
            return Optional.ofNullable(item);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public List<Item> findAll(int page, int size, String sortProperty, String sortDirection) {
        String sql = String.format(
            "SELECT id, name, price, image_url FROM products ORDER BY %s %s LIMIT ? OFFSET ?",
            sortProperty, sortDirection);
        int offset = page * size;
        return jdbcTemplate.query(sql, itemRowMapper, size, offset);
    }

//    public void deleteById(Long id) {
//        items.remove(id);
//    }
}
