package gift.controller;

import gift.dto.request.RequestGift;
import gift.dto.request.RequestModifyGift;
import gift.repository.GiftRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class GiftController {
    private final GiftRepository giftRepository;

    public GiftController(GiftRepository giftRepository) {
        this.giftRepository = giftRepository;
    }

    @PostMapping("")
    public ResponseEntity<?> addGift(@RequestBody RequestGift requestGift) {
        giftRepository.save(requestGift);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getGiftById(@PathVariable Long id) {
        return ResponseEntity.ok().body(giftRepository.findById(id));
    }

    @GetMapping("")
    public ResponseEntity<?> getAllGifts(){
        return ResponseEntity.ok().body(giftRepository.findAll());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateGift(@PathVariable Long id, @RequestBody RequestModifyGift requestModifyGift) {
        return ResponseEntity.ok().body(giftRepository.modify(id, requestModifyGift));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGift(@PathVariable Long id) {
        giftRepository.delete(id);
        return ResponseEntity.noContent().build();
    }
}
