package gift.product;


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
	public Long createItem(@RequestBody ItemRequest req) {
		if(req.name() == null || req.price() == null || req.imageUrl() == null)
			throw new RuntimeException("요청 데이터가 잘못됐습니다.");

		return itemService.createItem(req);
	}

	// 게시글 전체 조회
	@GetMapping()
	public List<Item> getAllItems() {
		return itemService.getAllItems();
	}

	// 게시글 단건 조회
	@GetMapping("/{itemId}")
	public Item getItem(@PathVariable Long itemId) {
		return itemService.getItem(itemId);
	}

	// 게시글 수정
	@PutMapping("/{itemId}")
	public Item updateItem(@PathVariable Long itemId, @RequestBody ItemRequest req) {
		if(req.name() == null || req.price() == null || req.imageUrl() == null)
			throw new RuntimeException("요청 데이터가 잘못됐습니다.");

		return itemService.updateItem(itemId, req);
	}
}
