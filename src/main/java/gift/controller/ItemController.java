package gift.controller;


import gift.dto.ItemDTO;
import gift.service.ItemService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<ItemDTO> addItems(
            @RequestBody ItemDTO dto
    ) {
        ItemDTO item = itemService.saveItem(dto);
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ItemDTO>> getItems(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer price
    ) {
        List<ItemDTO> items = itemService.getItems(name, price);
        return ResponseEntity.ok(items);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteItems(
            @RequestParam(required = false) String name
    ) {
        itemService.delete(name);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemDTO> updateItems(
            @PathVariable Long id,
            @RequestBody ItemDTO dto
    ) {
        ItemDTO item = itemService.updateItem(id, dto);
        return  ResponseEntity.ok(item);
    }
}
