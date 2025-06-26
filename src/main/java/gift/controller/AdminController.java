package gift.controller;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/admin/products")
public class AdminController {

    private final ProductService productService;

    public AdminController(ProductService productService) {
        this.productService = productService;
    }

    // 상품 추가 페이지로 이동
    @GetMapping("/new")
    public String goNewProductForm() {
        return "admin/newProductForm";
    }

    // 상품 수정 페이지로 이동
    @GetMapping("/{id}/edit")
    public String editProductForm(@PathVariable Long id, Model model) {

        ProductResponseDto product = productService.findProductById(id);
        model.addAttribute("product", product);

        return "admin/editProductForm";
    }
}