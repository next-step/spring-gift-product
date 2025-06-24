package gift.service;

import gift.dto.GiftRequestDto;
import gift.dto.GiftResponseDto;
import gift.entity.Gift;
import gift.repository.GiftRepository;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class GiftServiceImpl implements GiftService {

  private final GiftRepository giftRepository;

  public GiftServiceImpl(GiftRepository giftRepository) {
    this.giftRepository = giftRepository;
  }

  @Override
  public GiftResponseDto saveGift(GiftRequestDto dto) {
    Gift gift = new Gift(
        dto.getName(),
        dto.getPrice(),
        dto.getImageUrl()
    );
    return giftRepository.saveGift(gift);
  }

  @Override
  public List<GiftResponseDto> findAll() {
    return giftRepository.findAllGifts();
  }

  @Override
  public Optional<GiftResponseDto> findById(Long id) {
    return giftRepository.findById(id)
                         .map(gift -> new GiftResponseDto(
                             gift.getId(),
                             gift.getName(),
                             gift.getPrice(),
                             gift.getImageUrl()
                         ));
  }

  @Override
  public GiftResponseDto update(Long id, Map<String, Object> updates) {
    Gift gift = giftRepository.findById(id)
                              .orElseThrow(() -> new IllegalArgumentException(
                                  "해당 ID의 선물이 존재하지 않습니다: " + id));

    if (updates.containsKey("name")) {
      gift.setName((String) updates.get("name"));
    }
    if (updates.containsKey("price")) {
      gift.setPrice((Integer) updates.get("price"));
    }
    if (updates.containsKey("imageUrl")) {
      gift.setImageUrl((String) updates.get("imageUrl"));
    }

    giftRepository.updateGift(gift);

    return new GiftResponseDto(
        gift.getId(),
        gift.getName(),
        gift.getPrice(),
        gift.getImageUrl()
    );
  }

  @Override
  public void deleteGift(Long id) {
    giftRepository.deleteGiftById(id);
  }
}
