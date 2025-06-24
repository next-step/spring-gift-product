package gift.repository;

import gift.dto.ItemDto;
import gift.entity.Item;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class itemRepositoryImpl implements ItemRepository {

    private final Map<Long, Item> items = new HashMap<>();

    @Override
    public Item saveItem(ItemDto dto) {
        Item item = new Item(dto.getId(), dto.getName(), dto.getPrice(), dto.getImageUrl());
        items.put(dto.getId(), item);
        return item;
    }

    @Override
    public List<ItemDto> findItems(String name) {
        List<ItemDto> result = new ArrayList<>();
        for (Item item : items.values()) {
            if (name.equals(item.getName())) {
                result.add(new ItemDto(item));
            }
        }
        return result;
    }
}
