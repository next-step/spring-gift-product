package gift.service;

import gift.dto.ItemDTO;

import java.util.List;

public interface ItemService {
    ItemDTO saveItem(ItemDTO dto);

    List<ItemDTO> getItems(String name, Integer price);
}
