package gift.repository;

import gift.entity.Item;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepository {

    private final Map<Long, Item> items = new HashMap<>();

    public Item save(Item item) {
        items.put(item.getId(), item);
        return item;
    }

    public Optional<Item> findById(Long id) {
        return Optional.ofNullable(items.get(id));
    }

    public List<Item> findAll(int page, int size, String sortProperty, String sortDirection) {
        List<Item> allItems = new ArrayList<>(items.values());

        // 카테고리 Id를 필터링 하는 부분을 제외하고 페이지네이션을 구현함. 다음 과제에서 추가 예정

        if (sortProperty != null && !sortProperty.isEmpty()) {
            Comparator<Item> comparator = switch (sortProperty) {
                case "id" -> Comparator.comparing(Item::getId);
                case "name" -> Comparator.comparing(Item::getName);
                case "price" -> Comparator.comparing(Item::getPrice);
                default -> Comparator.comparing(Item::getId);
            };
            if ("desc".equalsIgnoreCase(sortDirection)) {
                comparator = comparator.reversed();
            }
            allItems.sort(comparator);
        }

        int startIndex = page * size;
        int endIndex = Math.min(startIndex + size, allItems.size());

        if (startIndex >= allItems.size() || startIndex < 0) {
            return new ArrayList<>();
        }

        return allItems.subList(startIndex, endIndex);
    }
}
