package gift.item.controller;

import gift.item.dto.ItemDto;
import gift.item.service.ItemService;
import org.springframework.http.HttpStatus;
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

    @DeleteMapping("/api/products")
    public ResponseEntity<Void> deleteItems(
            @RequestParam(required = false) String name
    ) {
        itemService.deleteItem(name);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/api/products/{id}")
    public ResponseEntity<ItemDto> updateItems(
            @PathVariable Long id,
            @RequestBody ItemDto dto
    ) {
        ItemDto updateItem = itemService.updateItem(id, dto);
        return ResponseEntity.ok(updateItem);
    }


}
