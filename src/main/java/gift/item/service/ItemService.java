package gift.item.service;

import gift.item.Item;
import gift.item.dto.ItemResponseDto;
import gift.item.repository.ItemRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public ItemResponseDto findItem(Long itemId) {
        Item item = itemRepository.findById(itemId);
        return new ItemResponseDto(
            item.getId(),
            item.getName(),
            item.getPrice(),
            item.getImageUrl()
        );
    }
}
