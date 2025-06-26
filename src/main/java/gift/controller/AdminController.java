package gift.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import gift.dto.CreateProductRequest;
import gift.dto.ReadProductResponse;
import gift.service.ProductService;

@Controller
@RequestMapping("/admin/products")
public class AdminController {

    private final ProductService productService;

    public AdminController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String adminPage(Model model) {
        List<ReadProductResponse> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "admin";
    }

    @GetMapping("/add")
    public String addPage() {
        return "addProduct";
    }

    @PostMapping
    public String addProduct(
        @ModelAttribute CreateProductRequest request
    ) {
        productService.createProduct(request);
        return "redirect:/admin/products";
    }
}
