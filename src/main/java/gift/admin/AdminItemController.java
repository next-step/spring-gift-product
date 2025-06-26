package gift.admin;

import gift.item.dto.ItemCreateDto;
import gift.item.dto.ItemResponseDto;
import gift.item.service.ItemService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminItemController {

    private final ItemService itemService;

    public AdminItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    // 페이지 뷰 반환

    @GetMapping("/admin/items")
    public String adminItemPage(Model model) {
        List<ItemResponseDto> items = itemService.findAll();
        model.addAttribute("items", items);
        return "admin-item";
    }

    @GetMapping("admin/items/new")
    public String newItemPage(Model model) {
        // dto가 record 형태라 생성시 값이 필요함.
        // 임의의 데이터를 넣어도 클라이언트에서 제출 시 새로운 객체가 생성됨.
        model.addAttribute("item", new ItemCreateDto("", 0, ""));
        return "admin-item-new";
    }

    // 상품 요청 처리

    @PostMapping("/admin/items")
    public String newItem(@ModelAttribute ItemCreateDto item) {
        itemService.createItem(item);
        return "redirect:/admin/items";
    }

}
