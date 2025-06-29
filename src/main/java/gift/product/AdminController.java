package gift.product;


import gift.product.dto.GetItemResponse;
import gift.product.dto.ItemRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/admin/products")
public class AdminController {

	private final ItemService itemService;

	public AdminController(ItemService itemService) {
		this.itemService = itemService;
	}

	// 1. 목록 조회
	@GetMapping
	public String list(Model model) {
		List<GetItemResponse> products = itemService.getAllItems();
		model.addAttribute("products", products);
		return "list";
	}

	// 2. 게시글 생성 페이지 이동
	@GetMapping("/new")
	public String createPage(Model model) {
		// 페이지 이동 시 itemRequest 빈 객체를 같이 갖고 들어감
		model.addAttribute("itemRequest", new ItemRequest("",0,""));
		return "form";
	}

	// 3. 게시글 등록처리
	@PostMapping
	public String create(@ModelAttribute @Valid ItemRequest req) {
		itemService.createItem(req);
		return "redirect:/admin/products";
	}

	// 4. 게시글 수정 페이지 이동
	// 생성과 동일한 form.html로 이동하지만 이미 존재하는 아이템값을 서비스에서 가져와 모델에 박아넣고 이동하는 중요한 차이점 존재
	@GetMapping("/{id}/edit")
	public String edit(@PathVariable Long id, Model model) {
		GetItemResponse item = itemService.getItem(id);
		model.addAttribute("itemRequest", new ItemRequest(item.name(), item.price(), item.imageUrl()));
		model.addAttribute("editId", item.id()); // 생성인지 수정인지를 가르는 키 값
		return "form";
	}

	// 5. 게시글 수정처리
	@PostMapping("/{id}/edit")
	public String update(@PathVariable Long id, @ModelAttribute @Valid ItemRequest req) {
		itemService.updateItem(id, req);
		return "redirect:/admin/products";
	}


	// 6. 게시글 삭제처리
	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id) {
		itemService.deleteItem(id);
		return "redirect:/admin/products";
	}

}
