package gift.service;

import gift.dto.ItemRequest;
import gift.dto.ItemResponse;
import gift.entity.Item;
import gift.repository.ItemRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public ItemResponse createItem(ItemRequest request) {
        Item item = new Item(null, request.name(), request.price(), request.imageUrl());
        Item savedItem = itemRepository.save(item);
        return ItemResponse.from(savedItem);
    }

    public ItemResponse getItemById(Long id) {
        Item item = itemRepository.findById(id)
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "상품을 찾을 수 없습니다: " + id));
        return ItemResponse.from(item);
    }

    public List<ItemResponse> getAllItems(int page, int size, String sortProperty,
        String sortDirection) {
        List<Item> items = itemRepository.findAll(page, size, sortProperty, sortDirection);
        return items.stream()
            .map(ItemResponse::from)
            .collect(Collectors.toList());
    }

    public ItemResponse updateItem(Long id, ItemRequest request) {
        Item existingItem = itemRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "수정하려는 상품을 찾을 수 없습니다: " + id));
        existingItem.updateItemInfo(request.name(), request.price(), request.imageUrl());
        Item updatedItem = itemRepository.save(existingItem);
        return ItemResponse.from(updatedItem);
    }

    public void deleteItem(Long id) {
        itemRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "삭제하려는 상품을 찾을 수 없습니다: " + id));
        itemRepository.deleteById(id);
    }
}
