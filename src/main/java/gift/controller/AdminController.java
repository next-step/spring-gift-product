package gift.controller;

import gift.dto.ProductResponseDto;
import gift.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/products")
public class AdminController {
    private final ProductService productService;

    public AdminController(ProductService productService) {
        this.productService = productService;
    }

    // 상품 목록 조회
    @GetMapping
    public String list(Model model) {
        List<ProductResponseDto> products = productService.findAllProducts();
        model.addAttribute("products", products);

        return "admin/product/list";
    }

    // 상품 단건 조회
    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model){
        ProductResponseDto findProduct = productService.findProductById(id);
        model.addAttribute("product", findProduct);

        return "admin/product/detail";
    }
}
