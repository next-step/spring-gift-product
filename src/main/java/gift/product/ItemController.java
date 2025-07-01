package gift.product;


import gift.product.dto.GetItemResponse;
import gift.product.dto.ItemRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/products")
public class ItemController {

	private final ItemService itemService;

	// 생성자 주입 (원래 롬복이 해주던거)
	public ItemController(ItemService itemService) {this.itemService = itemService;}


	// 게시글 생성
	@PostMapping()
	public Long createItem(@Valid @RequestBody ItemRequest req) {
		return itemService.createItem(req);
	}

	// 게시글 전체 조회
	@GetMapping()
	public List<GetItemResponse> getAllItems() {
		return itemService.getAllItems();
	}

	// 게시글 단건 조회
	@GetMapping("/{itemId}")
	public GetItemResponse getItem(@PathVariable Long itemId) {
		return itemService.getItem(itemId);
	}

	// 게시글 수정
	@PutMapping("/{itemId}")
	public GetItemResponse updateItem(@PathVariable Long itemId, @Valid @RequestBody ItemRequest req) {
		if(req.name() == null || req.price() == null || req.imageUrl() == null)
			throw new RuntimeException("요청 데이터가 잘못됐습니다.");

		return itemService.updateItem(itemId, req);
	}

	// 게시글 삭제
	@DeleteMapping("/{itemId}")
	public void deleteItem(@PathVariable Long itemId) {
		itemService.deleteItem(itemId);
	}
}
