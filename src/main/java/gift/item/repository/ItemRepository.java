package gift.item.repository;

import gift.item.dto.CreateItemDto;
import gift.item.dto.ItemDto;
import gift.item.entity.Item;

import java.util.List;

public interface ItemRepository {

    Item saveItem(CreateItemDto dto);

    List<ItemDto> findAllItems();

    Item findItem(Long id);

    Item updateItem(Long id, ItemDto dto);

    void deleteItem(Long id);
}

