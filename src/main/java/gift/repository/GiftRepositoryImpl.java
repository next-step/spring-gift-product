package gift.repository;

import gift.dto.GiftResponseDto;
import gift.entity.Gift;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class GiftRepositoryImpl implements GiftRepository {

  private final Map<Long, Gift> giftStorage = new HashMap<>();
  private long nextId = 1L;

  @Override
  public GiftResponseDto saveGift(Gift gift) {
    gift.setId(nextId++);
    giftStorage.put(gift.getId(), gift);
    return toDto(gift);
  }

  @Override
  public List<GiftResponseDto> findAllGifts() {
    List<GiftResponseDto> result = new ArrayList<>();
    for (Gift gift : giftStorage.values()) {
      result.add(toDto(gift));
    }
    return result;
  }

  @Override
  public Optional<Gift> findById(Long id) {
    return Optional.ofNullable(giftStorage.get(id));
  }

  @Override
  public void updateGift(Gift gift) {
    giftStorage.put(gift.getId(), gift);
  }

  @Override
  public void deleteGiftById(Long id) {
    giftStorage.remove(id);
  }

  private GiftResponseDto toDto(Gift gift) {
    return new GiftResponseDto(
        gift.getId(),
        gift.getName(),
        gift.getPrice(),
        gift.getImageUrl()
    );
  }
}
