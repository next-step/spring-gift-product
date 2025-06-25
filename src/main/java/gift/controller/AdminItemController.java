package gift.controller;


import gift.dto.ItemDTO;
import gift.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/products")
public class AdminItemController {

    private final ItemService itemService;

    public AdminItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public String list(
            Model model,
            @RequestParam String name,
            @RequestParam Integer price) {
        List<ItemDTO> items = itemService.getItems(name, price);
        model.addAttribute("items", items);
        return "admin/list";
    }

}