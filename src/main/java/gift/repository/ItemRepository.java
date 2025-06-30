package gift.repository;

import gift.entity.Item;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepository {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public ItemRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
            .withTableName("products")
            .usingGeneratedKeyColumns("id");
    }

    private final RowMapper<Item> itemRowMapper = (rs, rowNum) -> new Item(
        rs.getLong("id"),
        rs.getString("name"),
        rs.getInt("price"),
        rs.getString("image_url")
    );

    public Item save(Item item) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", item.getName());
        params.put("price", item.getPrice());
        params.put("image_url", item.getImageUrl());

        Long id = simpleJdbcInsert.executeAndReturnKey(params).longValue();

        return new Item(id, item.getName(), item.getPrice(), item.getImageUrl());
    }

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
