package gift.repository;

import gift.dto.ItemDTO;
import gift.entity.Item;

import java.util.List;

public interface ItemRepository {
    Item saveItem(Item dto);

    List<Item> getItems(String name, Integer price);

    Item deleteItems(String name);

    Item findById(Long id);

    List<Item> getAllItems();

    Item deleteById(Long id);


}
