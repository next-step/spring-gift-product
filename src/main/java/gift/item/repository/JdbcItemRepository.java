package gift.item.repository;

import gift.item.Item;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class JdbcItemRepository implements ItemRepository {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleInsert;

    public JdbcItemRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleInsert = new SimpleJdbcInsert(jdbcTemplate)
            .withTableName("item")
            .usingGeneratedKeyColumns("id");
    }

    @Override
    public Item findById(Long id) {
        String sql = "SELECT id, name, price, image_url FROM item WHERE id = ?";

        try {
            return jdbcTemplate.queryForObject(sql,
                (rs, rowNum) -> new Item(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getInt("price"),
                    rs.getString("image_url")
                ), id
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Item> findAll() {
        String sql = "SELECT id, name, price, image_url FROM item";

        return jdbcTemplate.query(sql,
            (rs, rowNum) -> new Item(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getInt("price"),
                rs.getString("image_url")
            )
        );
    }

    @Override
    public Item save(Item item) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", item.getName());
        params.put("price", item.getPrice());
        params.put("image_url", item.getImageUrl());

        Long generatedValue = simpleInsert.executeAndReturnKey(params).longValue();
        item.setId(generatedValue);

        return item;
    }

    @Override
    public Item update(Item item) {
        String sql = "UPDATE item SET name = ?, price = ?, image_url = ? WHERE id = ?";
        jdbcTemplate.update(sql, item.getName(), item.getPrice(), item.getImageUrl(), item.getId());
        return item;
    }

    @Override
    public void remove(Long id) {
        String sql = "DELETE FROM item WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
