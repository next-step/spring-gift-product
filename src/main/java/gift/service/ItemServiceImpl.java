package gift.service;


import gift.dto.ItemDTO;
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
    public ItemDTO saveItem(ItemDTO dto) {
        Item item = itemRepository.saveItem(dto);

        return new ItemDTO(item);
    }

    @Override
    public List<ItemDTO> getItems(String name, Integer price) {
        List<ItemDTO> items;
        System.out.println(name);
        System.out.println(price);
        if (name == null && price == null) {
            items = itemRepository.getAllItems();
        }else
            items = itemRepository.getItems(name, price);
        if(items.isEmpty()){
            System.out.println("예외 처리 실행");
            throw new ItemNotFoundException();
        }
        return items;
    }

    @Override
    public void delete(String name) {
        Item item =itemRepository.deleteItems(name);
        if(item == null){
            throw new ItemNotFoundException(name);
        }
    }

    @Override
    public ItemDTO updateItem(Long id, ItemDTO dto) {
        Item item = itemRepository.findById(id);
        if(item != null) {
            if (dto.getId().equals(item.getId())) {
                item.setName(dto.getName());
                item.setPrice(dto.getPrice());
                item.setImageUrl(dto.getImageUrl());
            }
            return new ItemDTO(item);
        }else
            throw new ItemNotFoundException();

    }

    @Override
    public ItemDTO findById(Long id) {
        List<ItemDTO> items = itemRepository.getAllItems();

        for(ItemDTO item : items){
            if(item.getId().equals(id)){
                return item;
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
    public List<ItemDTO> getAllItems() {
        List<ItemDTO> items = itemRepository.getAllItems();

        return items;
    }
}
