package gift.service;

import gift.dto.GiftRequestDto;
import gift.dto.GiftResponseDto;
import gift.dto.GiftUpdateDto;
import java.util.List;

public interface GiftService {

  GiftResponseDto saveGift(GiftRequestDto dto);

  List<GiftResponseDto> findAll();

  GiftResponseDto findById(Long id);

  GiftResponseDto update(Long id, GiftUpdateDto updateDto);

  void deleteGift(Long id);
}
