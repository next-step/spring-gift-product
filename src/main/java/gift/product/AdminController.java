package gift.product;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/admin/products")
public class AdminController {

	private final ItemService itemService;

	public AdminController(ItemService itemService) {
		this.itemService = itemService;
	}

	/** 1) 목록 페이지 */
	@GetMapping
	public String list(Model model) {
		List<Item> products = itemService.getAllItems();
		model.addAttribute("products", products);
		return "list";
	}

}
