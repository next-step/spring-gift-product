package gift.repository;

import gift.dto.ItemDTO;
import gift.entity.Item;

import java.util.List;

public interface ItemRepository {
    Item saveItem(ItemDTO dto);

    List<ItemDTO> getItems(String name, Integer price);

    void deleteItems(String name);
}
