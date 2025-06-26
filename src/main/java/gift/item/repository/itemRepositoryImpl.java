package gift.item.repository;

import gift.item.dto.ItemDto;
import gift.item.entity.Item;
import gift.item.exception.ItemNotFoundException;
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

    @Override
    public void deleteItem(String name) {
        for (Item item : items.values()) {
            if (name.equals(item.getName())) {
                items.remove(item.getId());
            }
        }
    }

    @Override
    public Item updateItem(Long id, ItemDto dto) {
        Item item = items.get(id);
        if (item == null) {
            throw new ItemNotFoundException("상품을 찾을 수 없습니다: " + id);
        }
        item.setName(dto.getName());
        item.setPrice(dto.getPrice());
        item.setImageUrl(dto.getImageUrl());
        return item;
    }
}
