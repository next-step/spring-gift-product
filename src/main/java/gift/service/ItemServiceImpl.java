package gift.service;


import gift.dto.ItemDTO;
import gift.entity.Item;
import gift.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public ItemDTO saveItem(ItemDTO dto) {
        Item item = itemRepository.saveItem(dto);

        return new ItemDTO(item);
    }

    @Override
    public List<ItemDTO> getItems(String name, Integer price) {

        List<ItemDTO> items = itemRepository.getItems(name, price);

        return items;
    }

    @Override
    public void delete(String name) {
        itemRepository.deleteItems(name);
    }
}
