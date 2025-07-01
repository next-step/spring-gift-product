package gift;


import gift.product.Item;
import gift.product.ItemRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@JdbcTest // jdbc 관련 빈만 띄울때 사용 for 속도
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(ItemRepositoryImpl.class)
@ActiveProfiles("test")
public class ItemRepositoryTest {
	@Autowired
	ItemRepositoryImpl itemRepository;

	@Test
	void 저장_후_단건조회() {
		// given
		Item item = new Item("테스트 상품", 1234, "url");
		// when -> 테스트할 기능을 실행하는 부분
		Long savedId = itemRepository.save(item);
		Optional<Item> getItem = itemRepository.findById(savedId);
		//then -> 결과값 검증
		assertThat(getItem).isPresent();
		assertThat(getItem.get().getId()).isEqualTo(savedId);
	}


	@Test
	void 목록조회_후_삭제() {
		// given
		List<Item> items = itemRepository.findAll();
		assertThat(items.size()).isEqualTo(15);
		// when
		itemRepository.deleteById(items.get(0).getId());
		// then
		assertThat(itemRepository.findAll().size()).isEqualTo(14);

	}

	@Test
	void 수정잘되냐() {
		//given
		Optional<Item> item = itemRepository.findById(1L);
		assertThat(item).isPresent();
		assertThat(item.get().getId()).isEqualTo(1L);
		//when
		itemRepository.update(new Item(1L, "수정된이름", 99, "http://www.naver.com"));
		//then
		assertThat(itemRepository.findById(1L).get().getName()).isEqualTo("수정된이름");
	}

}
