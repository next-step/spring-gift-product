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
        if (itemRepository.findById(request.id()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                "이미 존재하는 상품 ID입니다: " + request.id());
        }
        Item item = new Item(request.id(), request.name(), request.price(), request.imageUrl());
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
}
