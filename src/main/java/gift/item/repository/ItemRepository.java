package gift.item.repository;

import gift.item.Item;
import java.util.List;
import java.util.Optional;

public interface ItemRepository {

    Optional<Item> findById(Long id);

    List<Item> findAll();

    Item save(Item item);

    Item update(Item item);

    void remove(Long id);

}
