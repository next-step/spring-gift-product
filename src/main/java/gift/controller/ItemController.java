package gift.controller;


import gift.dto.ItemDTO;
import gift.service.ItemService;
import org.springframework.http.HttpEntity;
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
    public ResponseEntity<ItemDTO> addItems(
            @RequestBody ItemDTO dto
    ) {
        ItemDTO saved = itemService.saveItem(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/api/products")
    public ResponseEntity<List<ItemDTO>> getItems(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer price
    ) {
        List<ItemDTO> items = itemService.getItems(name, price);
        return ResponseEntity.ok(items);
    }

    @DeleteMapping("/api/products")
    public ResponseEntity<Void> deleteItems(
            @RequestParam(required = false) String name
    ) {
        itemService.delete(name);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
