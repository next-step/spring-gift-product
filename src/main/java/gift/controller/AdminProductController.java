package gift.controller;

import gift.dto.request.ProductRequest;
import gift.dto.response.ProductResponse;
import gift.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/products")
public class AdminProductController {

    private final ProductService productService;

    public AdminProductController(ProductService productService) {
        this.productService = productService;
    }

    // 목록 조회 화면
    @GetMapping
    public String showList(Model model) {
        List<ProductResponse> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "admin/product-list";
    }

    // 상품 등록
    @GetMapping("/new")
    public String showCreateForm() {
        return "admin/product-register";
    }

}