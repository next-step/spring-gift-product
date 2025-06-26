package gift.controller;

import gift.dto.GiftRequestDto;
import gift.dto.GiftResponseDto;
import gift.dto.GiftUpdateDto;
import gift.service.GiftService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gifts")
public class GiftController {

  private final GiftService giftService;

  public GiftController(GiftService giftService) {
    this.giftService = giftService;
  }

  @PostMapping
  public ResponseEntity<GiftResponseDto> addGift(@RequestBody GiftRequestDto dto) {
    return new ResponseEntity<>(giftService.saveGift(dto), HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<GiftResponseDto>> getAllGifts() {
    return new ResponseEntity<>(giftService.findAll(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<GiftResponseDto> getGiftById(@PathVariable Long id) {
    return giftService.findById(id)
                      .map(gift -> new ResponseEntity<>(gift, HttpStatus.OK))
                      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<GiftResponseDto> updateGift(
      @PathVariable Long id,
      @RequestBody GiftUpdateDto dto
  ) {
    GiftResponseDto updatedGift = giftService.update(id, dto);
    return new ResponseEntity<>(updatedGift, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteGift(@PathVariable Long id) {
    giftService.deleteGift(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
