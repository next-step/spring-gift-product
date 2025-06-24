package gift.controller;

import gift.dto.CreateItemDto;
import gift.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/api/products")
    public ResponseEntity<CreateItemDto> addItems(@RequestBody CreateItemDto dto) {
        CreateItemDto createItemDto = itemService.saveItem(dto);
        return ResponseEntity.ok(createItemDto);
    }


}
