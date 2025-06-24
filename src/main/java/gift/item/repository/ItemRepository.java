package gift.item.repository;

import gift.item.Item;
import java.util.List;

public interface ItemRepository {

    Item findById(Long id);

    List<Item> findAll();

    Item save(Item item);

    Item update(Item item);

}
