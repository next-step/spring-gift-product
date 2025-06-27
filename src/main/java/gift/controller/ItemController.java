package gift.controller;


import gift.dto.ItemCreateDTO;
import gift.dto.ItemDTO;
import gift.dto.ItemUpdateDTO;
import gift.service.ItemService;
import jakarta.validation.Valid;
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
    public ResponseEntity<ItemCreateDTO> addItems(
            @RequestBody @Valid ItemCreateDTO dto
    ) {
        ItemCreateDTO item = itemService.saveItem(dto);
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
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemUpdateDTO> updateItems(
            @PathVariable Long id,
            @RequestBody @Valid ItemUpdateDTO dto
    ) {
        ItemUpdateDTO item = itemService.updateItem(id, dto);
        return  ResponseEntity.ok(item);
    }
}
