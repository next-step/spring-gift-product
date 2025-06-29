package gift.service;

import gift.dto.ItemCreateDTO;
import gift.entity.Item;
import gift.repository.ItemRepositoryJdbc;
import org.springframework.stereotype.Service;

//@Service
public class ItemServiceImplV2 {
    private final ItemRepositoryJdbc itemRepository;

    public ItemServiceImplV2(ItemRepositoryJdbc itemRepository) {
        this.itemRepository = itemRepository;
    }

    public ItemCreateDTO saveItem(ItemCreateDTO dto) throws Exception {
        Item item = new Item(dto);
        Item saveditem = itemRepository.saveItem(item);
        return new ItemCreateDTO(saveditem);
    }
}
