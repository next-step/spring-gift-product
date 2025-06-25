package gift.service;

import gift.dto.ItemDTO;

import java.util.List;

public interface ItemService {
    ItemDTO saveItem(ItemDTO dto);

    List<ItemDTO> getItems(String name, Integer price);

    void delete(String name);

    ItemDTO updateItem(Long id, ItemDTO dto);


    ItemDTO findById(Long id);

    void deleteById(Long id);
}
