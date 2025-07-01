package gift.service;

import gift.dto.GiftRequestDto;
import gift.dto.GiftResponseDto;
import gift.dto.GiftUpdateDto;
import java.util.List;
import java.util.Optional;
import java.util.Map;

public interface GiftService {

    GiftResponseDto saveGift(GiftRequestDto dto);

    List<GiftResponseDto> findAll();

    Optional<GiftResponseDto> findById(Long id);  

    GiftResponseDto update(Long id, Map<String, Object> updates); 

    GiftResponseDto update(Long id, GiftUpdateDto updateDto); 

    void deleteGift(Long id);
}
