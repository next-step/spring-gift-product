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
    public String getItem(
            Model model,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer price) {
        List<ItemDTO> items = itemService.getItems(name, price);
        model.addAttribute("items", items);
        return "admin/list";
    }

    @PostMapping
    public String saveItem(@ModelAttribute ItemDTO itemDTO) {
        ItemDTO itemDTO1 = itemService.saveItem(itemDTO);
        return "redirect:/admin/products";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("itemDTO", new ItemDTO());
        return "admin/createForm";
    }
}
