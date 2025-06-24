package gift.controller;

import gift.dto.ItemRequest;
import gift.dto.ItemResponse;
import gift.service.ItemService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<ItemResponse> createItem(@RequestBody ItemRequest request) {
        ItemResponse newItem = itemService.createItem(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(newItem);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ItemResponse> getItemById(@PathVariable("productId") Long id) {
        ItemResponse item = itemService.getItemById(id);
        return ResponseEntity.ok(item);
    }

    @GetMapping
    public ResponseEntity<List<ItemResponse>> getAllItems(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "id") String sortProperty,
        @RequestParam(defaultValue = "asc") String sortDirection
    ) {
        List<ItemResponse> items = itemService.getAllItems(page, size, sortProperty, sortDirection);
        return ResponseEntity.ok(items);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ItemResponse> updateItem(@PathVariable("productId") Long id,
        @RequestBody ItemRequest request) {
        ItemResponse updatedItem = itemService.updateItem(id, request);
        return ResponseEntity.ok(updatedItem);
    }
}
