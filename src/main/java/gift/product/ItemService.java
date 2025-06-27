package gift.product;


import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class ItemService {

	private final Map<Long, Item> db = new HashMap<>();

	// 테스트를 위해 서버 재실행마다 15개의 아이템을 채워놓기
	@PostConstruct
	public void init() {
		for (int i = 1; i <= 15; i++) {
			Item item = new Item(
				"Item " + i,
				i * 1000,
				"url" + i
			);
			db.put(item.getId(), item);
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
		return db.values()
			.stream()
			.map(item -> new GetItemResponse(item.getId(), item.getName(), item.getPrice(), item.getImageUrl()))
			.collect(Collectors.toList());
	}


	public GetItemResponse getItem(Long itemId) {
		Item item = db.get(itemId);
		if (item == null) {
			throw new RuntimeException("해당 ID는 존재하지 않습니다!");
		}
		return new GetItemResponse(item.getId(), item.getName(), item.getPrice(), item.getImageUrl());
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
