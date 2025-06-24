package gift.item.controller;

import gift.item.dto.ItemCreateDto;
import gift.item.dto.ItemResponseDto;
import gift.item.service.ItemService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<ItemResponseDto> findItem(@PathVariable Long itemId) {
        ItemResponseDto dto = itemService.findItem(itemId);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<ItemResponseDto>> findAll() {
        List<ItemResponseDto> dtos = itemService.findAll();
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<ItemResponseDto> createItem(@RequestBody ItemCreateDto itemCreateDto) {
        ItemResponseDto dto = itemService.createItem(itemCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

}
