package gift.repository;

import gift.dto.ItemDto;
import gift.entity.Item;

import java.util.List;

public interface ItemRepository {
    Item saveItem(ItemDto dto);

    List<ItemDto> findItems(String name);
}

