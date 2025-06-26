package gift.service;

import gift.dto.GiftRequestDto;
import gift.dto.GiftResponseDto;
import gift.dto.GiftUpdateDto;
import gift.entity.Gift;
import gift.repository.GiftRepository;
import java.util.List;
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
  public GiftResponseDto findById(Long id) {
    Gift gift = giftRepository.findById(id)
                              .orElseThrow(() -> new IllegalArgumentException(
                                  "해당 ID의 선물이 존재하지 않습니다: " + id));
    return new GiftResponseDto(
        gift.getId(),
        gift.getName(),
        gift.getPrice(),
        gift.getImageUrl()
    );
  }


  @Override
  public GiftResponseDto update(Long id, GiftUpdateDto dto) {
    Gift gift = giftRepository.findById(id)
                              .orElseThrow(() -> new IllegalArgumentException(
                                  "해당 ID의 선물이 존재하지 않습니다: " + id));

    // dto 값이 null이 아니면 기존 값 덮어쓰기
    if (dto.getName() != null) {
      gift.setName(dto.getName());
    }
    if (dto.getPrice() != null) {
      gift.setPrice(dto.getPrice());
    }
    if (dto.getImageUrl() != null) {
      gift.setImageUrl(dto.getImageUrl());
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
