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

	private final ItemRepositoryImpl itemRepository;

	public ItemService(ItemRepositoryImpl itemRepository) {this.itemRepository = itemRepository;}


	public Long createItem(ItemRequest req) {

		Item item = new Item(req.name(), req.price(), req.imageUrl());
		
		return itemRepository.save(item);
	}

	public List<GetItemResponse> getAllItems() {

		List<Item> items = itemRepository.findAll();

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

		Item item = itemRepository.findById(itemId)
			.orElseThrow(() -> new RuntimeException("존재하지 않는 아이템입니다."));

		return new GetItemResponse(item.getId(), item.getName(), item.getPrice(), item.getImageUrl());
	}


	public GetItemResponse updateItem(Long itemId, ItemRequest req) {

		itemRepository.findById(itemId)
			.orElseThrow(() -> new RuntimeException("존재하지 않는 아이템입니다."));

		Item item = new Item(itemId, req.name(), req.price(), req.imageUrl());

		itemRepository.update(item);

		return getItem(itemId);
	}


	public void deleteItem(Long itemId) {

		itemRepository.findById(itemId)
			.orElseThrow(() -> new RuntimeException("존재하지 않는 아이템입니다."));

		itemRepository.deleteById(itemId);

	}

}
