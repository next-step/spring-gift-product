package gift.repository;

import gift.entity.Item;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepository {

    private final Map<Long, Item> items = new HashMap<>();

    public Optional<Item> findById(Long id) {
        return Optional.ofNullable(items.get(id));
    }
}
