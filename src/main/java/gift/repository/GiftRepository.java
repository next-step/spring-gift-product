package gift.repository;

import gift.dto.GiftResponseDto;
import gift.entity.Gift;
import java.util.List;
import java.util.Optional;

public interface GiftRepository {

  GiftResponseDto saveGift(Gift gift);

  List<GiftResponseDto> findAllGifts();

  Optional<Gift> findById(Long id);

  void updateGift(Gift gift);

  void deleteGiftById(Long id);
}
