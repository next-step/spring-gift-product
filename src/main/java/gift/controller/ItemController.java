package gift.controller;

import gift.dto.ItemResponse;
import gift.service.ItemService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

}
