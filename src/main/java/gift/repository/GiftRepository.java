package gift.repository;

import gift.entity.Gift;
import java.util.List;
import java.util.Optional;

public interface GiftRepository {

  Gift saveGift(Gift gift);            // Gift 엔티티 반환

  List<Gift> findAllGifts();           // 전체 Gift 목록 (엔티티 리스트)

  Optional<Gift> findById(Long id);    // 단건 조회

  void updateGift(Gift gift);          // 수정

  void deleteGiftById(Long id);        // 삭제
}
