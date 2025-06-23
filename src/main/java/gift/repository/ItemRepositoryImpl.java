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
    @Override
    public Item saveItem(ItemDTO dto) {
        Long id = dto.getId();
        String itemName = dto.getName();
        Integer itemPrice = dto.getPrice();
        String itemImageUrl = dto.getImageUrl();

        Item item = new Item(id, itemName, itemPrice, itemImageUrl);
        items.put(id, item);
        return item;
    }

    @Override
    public List<ItemDTO> getItems(String name, Integer price) {
        List<ItemDTO> result = new ArrayList<>();

        for (Item item : items.values()) {
            boolean nameMatch = (name == null || item.getName().equals(name));
            boolean priceMatch = (price == null || item.getPrice().equals(price));

            if (nameMatch && priceMatch) {
                ItemDTO dto = new ItemDTO(
                        item.getId(),
                        item.getName(),
                        item.getPrice(),
                        item.getImageUrl()
                );
                result.add(dto);
            }
        }

        return result;
    }

    @Override
    public void deleteItems(String name) {
        for(Item item : items.values()){
            if(item.getName().equals(name)){
                items.remove(item.getId());
            }
        }
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
}
