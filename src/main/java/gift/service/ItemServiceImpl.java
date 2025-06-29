package gift.service;


import gift.dto.ItemCreateDTO;
import gift.dto.ItemDTO;
import gift.dto.ItemResponseDTO;
import gift.dto.ItemUpdateDTO;
import gift.entity.Item;
import gift.exception.ItemNotFoundException;
import gift.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public ItemCreateDTO saveItem(ItemCreateDTO dto) {
        Item item = new Item(dto);
        Item saveditem = itemRepository.saveItem(item);

        return new ItemCreateDTO(saveditem);
    }

    @Override
    public List<ItemResponseDTO> getItems(String name, Integer price) {
        List<Item> items;
        List<ItemResponseDTO> result = new  ArrayList<>();
        if (name == null && price == null) {
            items = itemRepository.getAllItems();
        }else
            items = itemRepository.getItems(name, price);
        if(items.isEmpty()){
            System.out.println("예외 처리 실행");
            throw new ItemNotFoundException();
        }

        for (Item item : items) {
            result.add(ItemResponseDTO.from(item));

        }
        return result;
    }

    @Override
    public void delete(String name) {
        Item item =itemRepository.deleteItems(name);
        if(item == null){
            throw new ItemNotFoundException(name);
        }
    }

    @Override
    public ItemUpdateDTO updateItem(Long id, ItemUpdateDTO dto) {
        Item item = itemRepository.findById(id);
        if (item != null) {
            if (dto.id().equals(item.getId())) {
                Item updatedItem = itemRepository.updateItem(id, dto.name(), dto.price(), dto.imageUrl());
                return new ItemUpdateDTO(updatedItem);
            }else
                throw new ItemNotFoundException();
        } else
            throw new ItemNotFoundException();
    }

    @Override
    public ItemDTO findById(Long id) {
        List<Item> items = itemRepository.getAllItems();

        for(Item item : items){
            if(item.getId().equals(id)){
                return new ItemDTO(item);
            }
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        Item item = itemRepository.deleteById(id);
        if(item == null){
            throw new ItemNotFoundException();
        }
    }

    @Override
    public List<ItemResponseDTO> getAllItems() {
        List<Item> items = itemRepository.getAllItems();
        List<ItemResponseDTO> result = new ArrayList<>();

        for (Item item : items) {
            result.add(ItemResponseDTO.from(item));
        }

        return result;
    }
}
