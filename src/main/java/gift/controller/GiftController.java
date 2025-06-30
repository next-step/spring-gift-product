package gift.controller;

import gift.dto.request.RequestGift;
import gift.dto.request.RequestModifyGift;
import gift.repository.GiftJdbcRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/products")
public class GiftController {
    private final GiftJdbcRepository giftRepository;

    public GiftController(GiftJdbcRepository giftRepository) {
        this.giftRepository = giftRepository;
    }

    @PostMapping("")
    public ResponseEntity<?> addGift(@RequestBody RequestGift requestGift) {
        return new ResponseEntity<>(giftRepository.save(
                RequestGift.toEntity(requestGift)), HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getGiftById(@PathVariable Long id) {
        return ResponseEntity.ok().body(giftRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        ));
    }

    @GetMapping("")
    public ResponseEntity<?> getAllGifts(){
        return ResponseEntity.ok().body(giftRepository.findAll());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateGift(@PathVariable Long id, @RequestBody RequestModifyGift requestModifyGift) {
        giftRepository.findById(id).orElseThrow(() ->new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity
                .ok()
                .body(giftRepository.modify(id, RequestModifyGift.toEntity(requestModifyGift)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGift(@PathVariable Long id) {
        giftRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
