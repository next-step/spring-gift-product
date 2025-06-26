package gift.controller;


import gift.dto.ItemDTO;
import gift.entity.Item;
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
    public String searchItem(
            Model model,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer price) {

        List<ItemDTO> items;

        if(name == null && price == null){
            items = itemService.getAllItems();
        }else {
            items = itemService.getItems(name, price);
        }
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

    @PostMapping("/delete")
    public String deleteItem(@RequestParam Long id) {
        ItemDTO item = itemService.findById(id);
        if (item != null) {
            itemService.deleteById(id);
        }
        return "redirect:/admin/products";
    }

    @PostMapping("/{id}/edit")
    public String updateItem(@PathVariable Long id, @ModelAttribute ItemDTO dto) {
        itemService.updateItem(id, dto);
        return "redirect:/admin/products";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        ItemDTO item = itemService.findById(id);
        model.addAttribute("itemDTO", item);
        return "admin/editForm";
    }


}
