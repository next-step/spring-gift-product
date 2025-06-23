package gift.item.repository;

import gift.item.Item;

public interface ItemRepository {

    Item findById(Long id);

}
