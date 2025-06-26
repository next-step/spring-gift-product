package gift.admin;

import gift.item.dto.ItemResponseDto;
import gift.item.service.ItemService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminItemController {

    private final ItemService itemService;

    public AdminItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/admin/items")
    public String adminItemPage(Model model) {
        List<ItemResponseDto> items = itemService.findAll();
        model.addAttribute("items", items);
        return "admin-item";
    }
}
