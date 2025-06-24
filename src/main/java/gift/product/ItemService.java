package gift.product;


import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ItemService {

	private final List<Item> db = new ArrayList<>();

	// 테스트위해 서버 재실행마다 항상 2개는 채워놓기
	@PostConstruct
	public void init() {
		db.add(new Item("egg", 7900, "url1"));
		db.add(new Item("Nvidia Geforce 5060ti", 700000, "url2"));
	}

	public Long createItem(ItemRequest req) {
		Item item = new Item(req.name(), req.price(), req.imageUrl());
		db.add(item);
		return item.getId();
	}

	public List<Item> getAllItems() {
		return db;
	}


	public Item getItem(Long itemId) {
		return db.stream()
			.filter(item -> item.getId() == itemId)
			.findFirst()
			.orElseThrow(() -> new RuntimeException("해당 ID는 존재하지 않습니다"));
	}


	public Item updateItem(Long itemId, ItemRequest req) {
		return db.stream()
			.filter(item -> item.getId() == itemId)
			.findFirst()
			.map(item -> {
				item.setName(req.name());
				item.setPrice(req.price());
				item.setImageUrl(req.imageUrl());
				return item;
			})
			.orElseThrow(() -> new RuntimeException("해당 ID는 존재하지 않습니다"));
	}

}
