package gift.item.service;

import gift.item.dto.CreateItemDto;
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

    public ItemDto saveItem(CreateItemDto dto) {
        Item item = itemRepository.saveItem(dto);
        return new ItemDto(item);
    }

    public ItemDto findItem(Long id) {
        Item item = itemRepository.findItem(id);
        if (item == null) {
            throw new ItemNotFoundException("상품이 존재하지 않습니다: " + id);
        }
        return new ItemDto(item);
    }

    public List<ItemDto> findAllItems() {
        return itemRepository.findAllItems();
    }

    public void deleteItem(Long id) {
        itemRepository.deleteItem(id);
    }

    public ItemDto updateItem(Long id, ItemDto dto) {
        Item item = itemRepository.updateItem(id, dto);
        return new ItemDto(item);
    }

}
