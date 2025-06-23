package gift.item.repository;

import gift.item.Item;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryItemRepository implements ItemRepository {

    private final Map<Long, Item> items = new HashMap<>();

    @Override
    public Item findById(Long id) {
        return items.get(id);
    }
}
