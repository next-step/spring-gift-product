package gift.product;


import gift.product.dto.GetItemResponse;
import gift.product.dto.ItemRequest;
import jakarta.annotation.PostConstruct;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class ItemService {

	private final JdbcTemplate jdbcTemplate;

	public ItemService(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}


	public Long createItem(ItemRequest req) {
		if(req.name() == null || req.price() == null || req.imageUrl() == null)
			throw new RuntimeException("요청 데이터가 잘못됐습니다.");

		final String sql = "INSERT INTO item (name, price, image_url) VALUES (?, ?, ?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, req.name());
			ps.setInt(2, req.price());
			ps.setString(3, req.imageUrl());
			return ps;
		}, keyHolder);

		return keyHolder.getKey().longValue();
	}

	public List<GetItemResponse> getAllItems() {

		final String sql = "select * from item";

		return jdbcTemplate.query(sql, (rs, rowNum) -> new GetItemResponse(
			rs.getLong("id"),
			rs.getString("name"),
			rs.getInt("price"),
			rs.getString("image_url")
		));
	}


	public GetItemResponse getItem(Long itemId) {

		final String sql = "select * from item where id = ?";

		return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new GetItemResponse(
			rs.getLong("ID"),
			rs.getString("NAME"),
			rs.getInt("PRICE"),
			rs.getString("IMAGE_URL")
		), itemId);
	}


	public GetItemResponse updateItem(Long itemId, ItemRequest req) {
		final String sql = "update item set name = ?, price = ?, image_url = ? where id = ?";
		jdbcTemplate.update(sql, req.name(), req.price(), req.imageUrl(), itemId);
		return getItem(itemId);
	}


	public void deleteItem(Long itemId) {
		final String sql = "delete from item where id = ?";
		jdbcTemplate.update(sql, itemId);
	}

}
