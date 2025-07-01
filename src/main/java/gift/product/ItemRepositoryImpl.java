package gift.product;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;


@Repository
public class ItemRepositoryImpl implements ItemRepository {

	private final JdbcTemplate jdbcTemplate;

	public ItemRepositoryImpl(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}


	@Override
	public Long save(Item item) {

		final String sql = "INSERT INTO item (name, price, image_url) VALUES (?, ?, ?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, item.getName());
			ps.setInt(2, item.getPrice());
			ps.setString(3, item.getImageUrl());
			return ps;
		}, keyHolder);

		return keyHolder.getKey().longValue();
	}


	@Override
	public Optional<Item> findById(Long id) {
		final String sql = "select * from item where id = ?";

		return Optional.ofNullable(jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new Item(
			rs.getLong("ID"),
			rs.getString("NAME"),
			rs.getInt("PRICE"),
			rs.getString("IMAGE_URL")
		), id));
	}


	@Override
	public List<Item> findAll() {
		final String sql = "select * from item";

		return jdbcTemplate.query(sql, (rs, rowNum) -> new Item(
			rs.getLong("id"),
			rs.getString("name"),
			rs.getInt("price"),
			rs.getString("image_url")
		));

	}


	@Override
	public void update(Item item) {
		final String sql = "update item set name = ?, price = ?, image_url = ? where id = ?";

		jdbcTemplate.update(sql, item.getName(), item.getPrice(), item.getImageUrl(), item.getId());
	}


	@Override
	public void deleteById(Long id) {
		final String sql = "delete from item where id = ?";
		jdbcTemplate.update(sql, id);
	}

}
