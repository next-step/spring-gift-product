package gift.item.repository;

import gift.item.dto.ItemDto;
import gift.item.entity.Item;

import java.util.List;

public interface ItemRepository {
    Item saveItem(ItemDto dto);

    List<ItemDto> findItems(String name);

    void deleteItem(String name);

    Item updateItem(Long id, ItemDto dto);
}

