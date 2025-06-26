package gift.controller;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import gift.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final ProductService productService;

    public AdminController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String fingAll(Model model) {
        List<ProductResponseDto> ProductResponseDtoList = productService.findAllProducts();
        model.addAttribute("products", ProductResponseDtoList);
        return "admin/product-list";
    }

    @GetMapping("/products-add")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "admin/product-add";
    }

    @PostMapping("/products-add")
    public String createProduct(@ModelAttribute ProductRequestDto dto) {
        productService.saveProduct(dto);
        return "redirect:/admin";
    }

}