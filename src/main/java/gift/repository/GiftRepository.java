package gift.repository;

import gift.entity.Gift;
import java.util.List;
import java.util.Optional;

public interface GiftRepository {

  Gift saveGift(Gift gift);       

  List<Gift> findAllGifts();         

  Optional<Gift> findById(Long id);  

  void updateGift(Gift gift);        

  void deleteGiftById(Long id);        
}
