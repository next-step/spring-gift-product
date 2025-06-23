package gift.controller;


import gift.dto.CreateItemDTO;
import gift.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/api/products")
    public ResponseEntity<CreateItemDTO> addItems(
            @RequestBody CreateItemDTO dto
    ) {
        CreateItemDTO saved = itemService.saveItem(dto);
        return ResponseEntity.ok(saved);
    }

}
