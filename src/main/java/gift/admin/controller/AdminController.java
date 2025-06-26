package gift.admin.controller;

import gift.item.entity.Item;
import gift.item.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class AdminController {

    private final ItemService itemService;

    public AdminController(ItemService itemService) {
        this.itemService = itemService;
    }

    //상품 전체 목록 조회
    @GetMapping("/api/admin/products")
    public String getProducts(Model model) {
        model.addAttribute("products", itemService.findAllItems());
        return "list";
    }


}
