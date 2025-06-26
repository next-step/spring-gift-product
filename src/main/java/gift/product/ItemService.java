package gift.product;


import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ItemService {

	private final Map<Long, Item> db = new HashMap<>();

	// 테스트위해 서버 재실행마다 항상 2개는 채워놓기
	@PostConstruct
	public void init() {
		Item item1 = new Item("egg", 7900, "url1");
		Item item2 = new Item("Nvidia Geforce 5060ti", 700000, "url2");

		db.put(item1.getId(), item1);
		db.put(item2.getId(), item2);
	}

	public Long createItem(ItemRequest req) {
		Item item = new Item(req.name(), req.price(), req.imageUrl());
		db.put(item.getId(), item);
		return item.getId();
	}

	public List<Item> getAllItems() {
		return db.values().stream().toList();
	}


	public Item getItem(Long itemId) {
		Item item = db.get(itemId);
		if (item == null) {
			throw new RuntimeException("해당 ID는 존재하지 않습니다!");
		}
		return item;
	}


	public Item updateItem(Long itemId, ItemRequest req) {
		Item item = getItem(itemId);
		item.setName(req.name());
		item.setPrice(req.price());
		item.setImageUrl(req.imageUrl());
		return item;
	}


	public void deleteItem(Long itemId) {
		Item item = getItem(itemId);
		db.remove(itemId);
	}

}
