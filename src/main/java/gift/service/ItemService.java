package gift.service;

import gift.dto.CreateItemDto;
import gift.entity.Item;
import gift.repository.ItemRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public CreateItemDto saveItem(CreateItemDto dto) {
        Item item = itemRepository.saveItem(dto);
        return new CreateItemDto(item);
    }

}
