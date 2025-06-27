package gift.controller;

import gift.dto.ProductRequest;
import gift.dto.ProductResponse;
import gift.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/products")
public class AdminController {

    private final ProductService productService;

    public AdminController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String showProductList(Model model) {
        List<ProductResponse> products = productService.findAllProducts();
        model.addAttribute("products", products);
        model.addAttribute("productRequest", new ProductRequest("", 0, ""));
        return "admin/product-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("productRequest", new ProductRequest("", 0, ""));
        return "admin/product-form";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute ProductRequest productRequest) {
        productService.addProduct(productRequest);
        return "redirect:/admin/products";
    }
}