package gift.crud;

import gift.dto.ItemCreateDTO;
import gift.dto.ItemUpdateDTO;
import gift.repository.ItemRepository;
import gift.repository.ItemRepositoryImpl;
import gift.service.ItemService;
import gift.service.ItemServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CRUDTest {

    private ItemService itemService;

    @BeforeEach
    void setUp() {
        ItemRepository itemRepository = new ItemRepositoryImpl();
        itemService = new ItemServiceImpl(itemRepository);
    }

    @Test
    void addItem() {
        ItemCreateDTO dto = new ItemCreateDTO("juice", 1000, "url");

        ItemCreateDTO savedItem = itemService.saveItem(dto);

        Assertions.assertThat(savedItem.name()).isEqualTo("juice");
        Assertions.assertThat(savedItem.price()).isEqualTo(1000);
        Assertions.assertThat(savedItem.imageUrl()).isEqualTo("url");
    }
}
