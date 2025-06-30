package gift.admin.controller;

import gift.item.dto.CreateItemDto;
import gift.item.dto.ItemDto;
import gift.item.dto.UpdateItemDto;
import gift.item.entity.Item;
import gift.item.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminController {

    private final ItemService itemService;

    public AdminController(ItemService itemService) {
        this.itemService = itemService;
    }

    //상품 전체 목록 조회 페이지
    @GetMapping("/admin/products")
    public String getAllProducts(Model model) {
        model.addAttribute("products", itemService.findAllItems());
        return "list";
    }

    //특정 상품 조회 (단건 조회) 페이지
    @GetMapping("/admin/products/{id}")
    public String getProductById(@PathVariable Long id, Model model) {
        model.addAttribute("product", itemService.findItem(id));
        return "detail";
    }

    //상품 등록 페이지
    @GetMapping("/admin/products/create")
    public String createProduct(Model model) {
        model.addAttribute("product", new CreateItemDto("", 0, ""));
        return "create";
    }

    //상품 등록하기 기능
    @PostMapping("/admin/products/create")
    public String createProduct(CreateItemDto dto) {
        itemService.saveItem(dto);
        return "redirect:/admin/products";
    }

    //상품 삭제하기 기능
    @DeleteMapping("/admin/products/{id}")
    public String deleteProduct(@PathVariable Long id) {
        itemService.deleteItem(id);
        return "redirect:/admin/products";
    }
    
    //상품 수정 페이지
    @GetMapping("/admin/products/edit/{id}")
    public String updateProduct(@PathVariable Long id, Model model) {
        model.addAttribute("product", itemService.findItem(id));
        return "update";
    }

    //상품 수정 기능
    @PutMapping("/admin/products/edit/{id}")
    public String updateProduct(@PathVariable Long id, UpdateItemDto dto) {
        itemService.updateItem(id, dto);
        return "redirect:/admin/products";
    }

}