package gift.product;


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
		List<Item> products = itemService.getAllItems();
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
	public String create(@ModelAttribute ItemRequest req) {
		itemService.createItem(req);
		return "redirect:/admin/products";
	}

	// 4. 게시글 수정 페이지 이동
	// 생성과 동일한 form.html로 이동하지만 이미 존재하는 아이템값을 서비스에서 가져와 모델에 박아넣고 이동하는 중요한 차이점 존재
	@GetMapping("/{id}/edit")
	public String edit(@PathVariable Long id, Model model) {
		Item item = itemService.getItem(id);
		model.addAttribute("itemRequest", new ItemRequest(item.getName(), item.getPrice(), item.getImageUrl()));
		model.addAttribute("editId", item.getId()); // 생성인지 수정인지를 가르는 키 값
		return "form";
	}

	// 5. 게시글 수정처리
	@PostMapping("/{id}/edit")
	public String update(@PathVariable Long id, @ModelAttribute ItemRequest req) {
		itemService.updateItem(id, req);
		return "redirect:/admin/products";
	}


}
