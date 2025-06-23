package gift.controller;

import gift.dto.request.RequestGift;
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
}
