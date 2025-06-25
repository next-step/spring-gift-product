package gift.controller;

import gift.dto.ProductResponseDto;
import gift.service.ProductService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/products")
public class AdminProductController {

    private final ProductService productService;

    public AdminProductController(ProductService productService) {
        this.productService = productService;
    }

    // 목록
    @GetMapping
    public String showProductList(Model model) {
        List<ProductResponseDto> products = productService.findAllProducts();
        model.addAttribute("products", products);
        return "product/list";
    }
}
