package gift.item.controller;

import gift.item.dto.CreateItemDto;
import gift.item.dto.ItemDto;
import gift.item.dto.UpdateItemDto;
import gift.item.service.ItemService;
import jakarta.validation.Valid;
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

    //상품 생성
    @PostMapping("/api/products")
    public ResponseEntity<ItemDto> addItem(
            @RequestBody @Valid CreateItemDto dto
    ) {
        ItemDto itemDto = itemService.saveItem(dto);
        return ResponseEntity.ok(itemDto);
    }

    //상품 전체 조회
    @GetMapping("/api/products")
    public ResponseEntity<List<ItemDto>> findAllItems() {
        List<ItemDto> items = itemService.findAllItems();
        return ResponseEntity.ok(items);
    }

    //특정 상품 조회
    @GetMapping("/api/products/{id}")
    public ResponseEntity<ItemDto> findItem(
            @PathVariable Long id
    ) {
        ItemDto item = itemService.findItem(id);
        return ResponseEntity.ok(item);
    }

    //상품 삭제
    @DeleteMapping("/api/products/{id}")
    public ResponseEntity<Void> deleteItem(
            @PathVariable  Long id
    ) {
        itemService.deleteItem(id);
        return ResponseEntity.ok().build();
    }

    //상품 수정
    @PutMapping("/api/products/{id}")
    public ResponseEntity<ItemDto> updateItem(
            @PathVariable Long id,
            @RequestBody @Valid UpdateItemDto dto
    ) {
        itemService.updateItem(id, dto);
        return ResponseEntity.noContent().build();
    }


}