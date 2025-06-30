package gift.item.repository;

import gift.item.dto.CreateItemDto;
import gift.item.dto.ItemDto;
import gift.item.dto.UpdateItemDto;
import gift.item.entity.Item;
import gift.item.exception.ItemNotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class itemRepositoryImpl implements ItemRepository {

    private final JdbcTemplate jdbcTemplate;

    public itemRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Item saveItem(CreateItemDto dto) {
        String sql = "INSERT INTO item (name, price, image_url) VALUES (?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, dto.getName());
            ps.setInt(2, dto.getPrice());
            ps.setString(3, dto.getImageUrl());
            return ps;
        }, keyHolder);

        Long id = keyHolder.getKey().longValue();

        return new Item(id, dto.getName(), dto.getPrice(), dto.getImageUrl());
    }

    @Override
    public List<ItemDto> findAllItems() {
        String sql = "SELECT * FROM item";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
            new ItemDto(
                new Item(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getInt("price"),
                    rs.getString("image_url")
                )
            )
        );
    }

    @Override
    public Item findItem(Long id) {
        String sql = "SELECT * FROM item WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) ->
            new Item(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getInt("price"),
                rs.getString("image_url")
            )
        );
    }

    @Override
    public Item updateItem(Long id, UpdateItemDto dto) {
        String sql = "UPDATE item SET name = ?, price = ?, image_url = ? WHERE id = ?";
        jdbcTemplate.update(sql, dto.getName(), dto.getPrice(), dto.getImageUrl(), id);
        return findItem(id);
    }

    @Override
    public void deleteItem(Long id) {
        String sql = "DELETE FROM item WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
