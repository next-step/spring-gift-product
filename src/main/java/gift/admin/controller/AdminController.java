package gift.admin.controller;

import gift.dto.ProductResponseDto;
import gift.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/management/products")
public class AdminController {
    private final ProductService productService;

    public AdminController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String showProductManagementPage(Model model) {
        List<ProductResponseDto> products = productService.findAllProducts();
        model.addAttribute("products", products);
        return "admin/product/list";
    }

    @GetMapping("/new")
    public String showAddProductForm() {
        return "admin/product/add";
    }

    @GetMapping("/{id}")
    public String showEditProductForm(@PathVariable("id") Long id, Model model) {
        ProductResponseDto product = productService.findProductById(id);
        model.addAttribute("product", product);
        return "admin/product/edit";
    }
}