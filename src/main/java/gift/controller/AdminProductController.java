package gift.controller;

import gift.dto.ProductAddRequestDto;
import gift.dto.ProductResponseDto;
import gift.dto.ProductUpdateRequestDto;
import gift.entity.Product;
import gift.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/admin/products")
public class AdminProductController {

    private final ProductService productService;

    public AdminProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String findAllProduct(Model model) {
        List<ProductResponseDto> products = productService.findAllProduct();
        model.addAttribute("products", products);
        return "admin/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("product", new ProductAddRequestDto());
        return "admin/add";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute ProductAddRequestDto requestDto) {
        productService.addProduct(requestDto);
        return "redirect:/api/admin/products";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(
            Model model,
            @PathVariable Long id
    ) {
        ProductResponseDto product = productService.findProductById(id);
        model.addAttribute("product", product);
        return "admin/edit";
    }

    @PostMapping("/edit/{id}")
    public String editProduct(
            @PathVariable Long id,
            @ModelAttribute ProductUpdateRequestDto requestDto
    ) {
        productService.updateProductById(id, requestDto);
        return "redirect:/api/admin/products";
    }
}
