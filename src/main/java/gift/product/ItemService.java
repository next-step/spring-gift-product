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

		final String sql = "INSERT INTO item (name, price, image_url) VALUES (?, ?, ?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();

		Item item = new Item(req.name(), req.price(), req.imageUrl());



		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, item.getName());
			ps.setInt(2, item.getPrice());
			ps.setString(3, item.getImageUrl());
			return ps;
		}, keyHolder);

		return keyHolder.getKey().longValue();
	}

	public List<GetItemResponse> getAllItems() {

		final String sql = "select * from item";

		List<Item> items =  jdbcTemplate.query(sql, (rs, rowNum) -> new Item(
			rs.getLong("id"),
			rs.getString("name"),
			rs.getInt("price"),
			rs.getString("image_url")
		));

		return items.stream()
			.map(
				item -> new GetItemResponse(
					item.getId(),
					item.getName(),
					item.getPrice(),
					item.getImageUrl()
				)
			).toList();
	}


	public GetItemResponse getItem(Long itemId) {

		final String sql = "select * from item where id = ?";

		// todo: 존재 확인 로직

		Item item =  jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new Item(
			rs.getLong("ID"),
			rs.getString("NAME"),
			rs.getInt("PRICE"),
			rs.getString("IMAGE_URL")
		), itemId);

		return new GetItemResponse(item.getId(), item.getName(), item.getPrice(), item.getImageUrl());
	}


	public GetItemResponse updateItem(Long itemId, ItemRequest req) {

		// todo: 존재 확인 로직


		final String sql = "update item set name = ?, price = ?, image_url = ? where id = ?";


		jdbcTemplate.update(sql, req.name(), req.price(), req.imageUrl(), itemId);
		return getItem(itemId);
	}


	public void deleteItem(Long itemId) {

		// todo: 존재 확인 로직


		final String sql = "delete from item where id = ?";
		jdbcTemplate.update(sql, itemId);
	}

}
