package gift.controller;

import gift.dto.ItemResponse;
import gift.service.ItemService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/items")
public class AdminItemController {

    private final ItemService itemService;

    public AdminItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public String listItems(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "id") String sortProperty,
        @RequestParam(defaultValue = "asc") String sortDirection,
        Model model
    ) {
        List<ItemResponse> items = itemService.getAllItems(page, size, sortProperty, sortDirection);
        model.addAttribute("items", items);
        return "admin/items/list";
    }
}
