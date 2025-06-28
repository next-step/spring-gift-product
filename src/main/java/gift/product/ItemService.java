package gift.product;


import gift.product.dto.GetItemResponse;
import gift.product.dto.ItemRequest;
import jakarta.annotation.PostConstruct;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class ItemService {

	private final JdbcTemplate jdbcTemplate;
	public ItemService(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}


	private final Map<Long, Item> db = new HashMap<>();




	// 테스트를 위해 서버 재실행마다 15개의 아이템을 채워놓기
	@PostConstruct
	public void init() {
		jdbcTemplate.execute("drop table if exists item");
		jdbcTemplate.execute("""
			CREATE TABLE item (
				id BIGINT PRIMARY KEY AUTO_INCREMENT,
				name VARCHAR(255) NOT NULL,
				price INT NOT NULL,
				image_url VARCHAR(1000) NOT NULL
			)
		""");

		for(int i=1; i<=15; i++){
			String name = "item" + i;
			Integer price = i * 1000;
			String imageUrl = "url"+i;

			jdbcTemplate.update("insert into item (name, price, image_url) values (?, ?, ?)", name, price, imageUrl);
		}


	}

	public Long createItem(ItemRequest req) {
		if(req.name() == null || req.price() == null || req.imageUrl() == null)
			throw new RuntimeException("요청 데이터가 잘못됐습니다.");

		Item item = new Item(req.name(), req.price(), req.imageUrl());
		db.put(item.getId(), item);
		return item.getId();
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
		Item item = db.get(itemId);
		item.setName(req.name());
		item.setPrice(req.price());
		item.setImageUrl(req.imageUrl());
		return new GetItemResponse(item.getId(), item.getName(), item.getPrice(), item.getImageUrl());
	}


	public void deleteItem(Long itemId) {
		Item item = db.get(itemId);
		db.remove(itemId);
	}

}
