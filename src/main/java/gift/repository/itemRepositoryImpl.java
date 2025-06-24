package gift.repository;

import gift.dto.CreateItemDto;
import gift.entity.Item;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class itemRepositoryImpl implements ItemRepository {

    private final Map<Long, Item> items = new HashMap<>();

    @Override
    public Item saveItem(CreateItemDto dto) {
        Item item = new Item(dto.getId(), dto.getName(), dto.getPrice(), dto.getImageUrl());
        items.put(dto.getId(), item);
        return item;
    }
}
