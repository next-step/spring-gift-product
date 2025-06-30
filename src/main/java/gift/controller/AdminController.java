package gift.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import gift.dto.CreateProductRequest;
import gift.dto.ProductResponse;
import gift.dto.UpdateProductRequest;
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
        List<ProductResponse> products = productService.getAllProducts();
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

    @GetMapping("/{id}/edit")
    public String editPage(
        @PathVariable Long id,
        Model model
    ) {
        ProductResponse product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "editProduct";
    }

    @PostMapping("/{id}")
    public String updateProduct(
        @PathVariable Long id,
        @ModelAttribute UpdateProductRequest request
    ) {
        productService.updateProduct(id, request);
        return "redirect:/admin/products";
    }

    @GetMapping("/{id}/delete")
    public String deleteProduct(
        @PathVariable Long id
    ) {
        productService.deleteProduct(id);
        return "redirect:/admin/products";
    }
}
