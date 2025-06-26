package gift.item.repository;

import gift.item.dto.CreateItemDto;
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
    private long sequence = 1L;

    @Override
    public Item saveItem(CreateItemDto dto) {
        Long id = sequence++;
        Item item = new Item(id, dto.getName(), dto.getPrice(), dto.getImageUrl());
        items.put(id, item);
        return item;
    }

    @Override
    public List<ItemDto> findAllItems() {
        List<ItemDto> result = new ArrayList<>();
        for (Item item : items.values()) {
            result.add(new ItemDto(item));
        }
        return result;
    }

    @Override
    public Item findItem(Long id) {
        return items.get(id);
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

    @Override
    public void deleteItem(Long id) {
        if (!items.containsKey(id)) {
            throw new ItemNotFoundException("상품을 찾을 수 없습니다: " + id);
        }
        items.remove(id);
    }
}
