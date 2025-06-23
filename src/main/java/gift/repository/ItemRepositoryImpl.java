package gift.repository;


import gift.dto.CreateItemDTO;
import gift.entity.Item;
import gift.service.ItemService;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ItemRepositoryImpl implements ItemRepository {

    private final Map<Long, Item> items = new HashMap<>();
    @Override
    public Item saveItem(CreateItemDTO dto) {
        Long id = dto.getId();
        String itemName = dto.getName();
        Integer itemPrice = dto.getPrice();
        String itemImageUrl = dto.getImageUrl();

        Item item = new Item(id, itemName, itemPrice, itemImageUrl);
        items.put(id, item);
        return item;
    }
}
