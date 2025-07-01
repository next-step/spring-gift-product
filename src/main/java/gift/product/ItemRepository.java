package gift.product;


import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface ItemRepository {

	// 생성
	Long save(Item item);

	// 단건조회
	Optional<Item> findById(Long id);

	// 목록조회
	List<Item> findAll();

	// 수정
	void update(Item item);

	// 삭제
	void deleteById(Long id);

}
