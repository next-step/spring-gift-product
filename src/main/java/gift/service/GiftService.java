package gift.service;

import gift.dto.GiftRequestDto;
import gift.dto.GiftResponseDto;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface GiftService {

  GiftResponseDto saveGift(GiftRequestDto dto);

  List<GiftResponseDto> findAll();

  Optional<GiftResponseDto> findById(Long id);

  GiftResponseDto update(Long id, Map<String, Object> updates);

  void deleteGift(Long id);
}
