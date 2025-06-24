package gift.item.service;

import gift.item.Item;
import gift.item.dto.ItemCreateDto;
import gift.item.dto.ItemResponseDto;
import gift.item.dto.ItemUpdateDto;
import gift.item.repository.ItemRepository;
import java.util.ArrayList;
import java.util.List;
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

    public List<ItemResponseDto> findAll() {
        List<Item> items = itemRepository.findAll();

        List<ItemResponseDto> itemResponseDtos = new ArrayList<>();

        for (Item item : items) {
            ItemResponseDto dto = new ItemResponseDto(
                item.getId(),
                item.getName(),
                item.getPrice(),
                item.getImageUrl()
            );
            itemResponseDtos.add(dto);
        }

        return itemResponseDtos;
    }

    public ItemResponseDto createItem(ItemCreateDto itemCreateDto) {
        Item newItem = new Item(
            itemCreateDto.getName(),
            itemCreateDto.getPrice(),
            itemCreateDto.getImageUrl()
        );

        Item savedItem = itemRepository.save(newItem);

        return new ItemResponseDto(
            savedItem.getId(),
            savedItem.getName(),
            savedItem.getPrice(),
            savedItem.getImageUrl()
        );
    }

    public ItemResponseDto updateItem(Long itemId, ItemUpdateDto itemUpdateDto) {
        Item oldItem = itemRepository.findById(itemId);

        oldItem.setName(itemUpdateDto.getName());
        oldItem.setPrice(itemUpdateDto.getPrice());
        oldItem.setImageUrl(itemUpdateDto.getImageUrl());

        Item updatedItem = itemRepository.save(oldItem);

        return new ItemResponseDto(
            updatedItem.getId(),
            updatedItem.getName(),
            updatedItem.getPrice(),
            updatedItem.getImageUrl()
        );
    }
}
