package gift.repository;


import gift.dto.ItemDTO;
import gift.entity.Item;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepositoryImpl implements ItemRepository {

    private final Map<Long, Item> items = new HashMap<>();
    private static Long sequence = 1L;
    @Override
    public Item saveItem(ItemDTO dto) {
        Long id = dto.getId();
        if (id == null) {
            id = sequence++;
        }
        String itemName = dto.getName();
        Integer itemPrice = dto.getPrice();
        String itemImageUrl = dto.getImageUrl();

        Item item = new Item(id, itemName, itemPrice, itemImageUrl);
        items.put(id, item);
        return item;
    }

    @Override
    public List<Item> getItems(String name, Integer price) {
        List<Item> result = new ArrayList<>();

        for (Item item : items.values()) {
            boolean nameMatch = (name == null || item.getName().equals(name));
            boolean priceMatch = (price == null || item.getPrice().equals(price));

            if (nameMatch && priceMatch) {
                Item addItem = new Item(item);
                result.add(addItem);
            }
        }

        return result;
    }

    @Override
    public Item deleteItems(String name) {
        for(Item item : items.values()){
            if(item.getName().equals(name)){
                return items.remove(item.getId());
            }
        }
        return null;
    }

    @Override
    public Item findById(Long id) {
        for(Item item : items.values()){
            if(item.getId().equals(id)){
                return item;
            }
        }
        return null;
    }

    @Override
    public List<Item> getAllItems() {
        List<Item> result = new ArrayList<>();
        for (Item item : items.values()) {
            result.add(new Item(item));
        }
        return result;
    }

    @Override
    public Item deleteById(Long id) {
        return items.remove(id);
    }

}
