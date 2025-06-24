package gift.controller;

import gift.dto.ItemDto;
import gift.entity.Item;
import gift.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/api/products")
    public ResponseEntity<ItemDto> addItems(@RequestBody ItemDto dto) {
        ItemDto itemDto = itemService.saveItem(dto);
        return ResponseEntity.ok(itemDto);
    }

    @GetMapping("/api/products")
    public ResponseEntity<List<ItemDto>> findItems(
            @RequestParam(required = false) String name
    ) {
        List<ItemDto> items = itemService.findItems(name);
        return ResponseEntity.ok(items);
    }


}
