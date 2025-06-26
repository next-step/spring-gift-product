package gift.controller;

import gift.dto.ProductResponseDto;
import gift.entity.Product;
import gift.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/products")
public class AdminProductController {

    private final ProductService productService;

    public AdminProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public String findAllProduct(Model model) {
        List<ProductResponseDto> products = productService.findAllProduct();
        model.addAttribute("products",products);
        return "admin/list";
    }
}
