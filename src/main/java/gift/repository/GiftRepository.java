package gift.repository;

import gift.entity.Gift;

import java.util.List;
import java.util.Optional;

public interface GiftRepository {
    Gift save(Gift gift);
    Optional<Gift> findById(Long id);
    List<Gift> findAll();
    Gift modify(Long id, Gift gift);
    void deleteById(Long id);
}
