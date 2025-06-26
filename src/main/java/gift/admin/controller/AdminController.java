package gift.admin.controller;

import gift.item.dto.CreateItemDto;
import gift.item.entity.Item;
import gift.item.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class AdminController {

    private final ItemService itemService;

    public AdminController(ItemService itemService) {
        this.itemService = itemService;
    }

    //상품 전체 목록 조회 페이지
    @GetMapping("/api/admin/products")
    public String getAllProducts(Model model) {
        model.addAttribute("products", itemService.findAllItems());
        return "list";
    }

    //특정 상품 조회 (단건 조회) 페이지
    @GetMapping("/api/admin/products/{id}")
    public String getProductById(@PathVariable Long id, Model model) {
        model.addAttribute("product", itemService.findItem(id));
        return "detail";
    }

    //상품 등록 페이지
    @GetMapping("/api/admin/products/create")
    public String createProduct(Model model) {
        model.addAttribute("product", new CreateItemDto("", 0, ""));
        return "create";
    }

    //상품 등록하기 기능
    @PostMapping("/api/admin/products/create")
    public String createProduct(CreateItemDto dto) {
        itemService.saveItem(dto);
        return "redirect:/api/admin/products";
    }



}
