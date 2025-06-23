package gift.service;


import gift.dto.CreateItemDTO;
import gift.entity.Item;
import gift.repository.ItemRepository;
import gift.repository.ItemRepositoryImpl;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public CreateItemDTO saveItem(CreateItemDTO dto) {
        Item item = itemRepository.saveItem(dto);

        return new CreateItemDTO(item);
    }
}
