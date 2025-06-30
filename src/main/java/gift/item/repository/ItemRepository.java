package gift.item.repository;

import gift.item.dto.CreateItemDto;
import gift.item.dto.ItemDto;
import gift.item.dto.UpdateItemDto;
import gift.item.entity.Item;

import java.util.List;

public interface ItemRepository {

    Item saveItem(Item item);

    List<Item> findAllItems();

    Item findItem(Long id);

    Item updateItem(Long id, UpdateItemDto dto);

    void deleteItem(Long id);
}