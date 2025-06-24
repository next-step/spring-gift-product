package gift.product;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ItemService {

	private final List<Item> db = new ArrayList<>();

	public Long createItem(CreateItemRequest req) {
		Item item = new Item(req.name(), req.price(), req.imageUrl());
		db.add(item);
		return item.getId();
	}

	public List<Item> getAllItems() {
		return db;
	}

}
