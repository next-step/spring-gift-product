package gift.item.service;

import gift.item.dto.ItemDto;
import gift.item.entity.Item;
import gift.item.exception.ItemNotFoundException;
import gift.item.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public ItemDto saveItem(ItemDto dto) {
        Item item = itemRepository.saveItem(dto);
        return new ItemDto(item);
    }

    public List<ItemDto> findItems(String name) {
        List<ItemDto> items = itemRepository.findItems(name);

        if (items.isEmpty()) {
            throw new ItemNotFoundException("상품이 존재하지 않습니다: " + name);
        }
        return items;
    }

    public void deleteItem(String name) {
        itemRepository.deleteItem(name);
    }

    public ItemDto updateItem(Long id, ItemDto dto) {
        Item item = itemRepository.updateItem(id, dto);
        return new ItemDto(item);
    }

}
