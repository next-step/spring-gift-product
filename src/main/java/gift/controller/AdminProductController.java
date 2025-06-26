package gift.controller;

import gift.service.ProductService;
import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;

@Controller
@RequestMapping("/admin/products")
public class AdminProductController {

    private final ProductService productService;

    public AdminProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String list(Model model) {
        List<ProductResponseDto> products = productService.getProducts();
        model.addAttribute("products", products);
        return "admin/list";
    }

    @GetMapping("/admin/products/new")
    public String showCreateForm() {
        return "admin/new";
    }

    @PostMapping("/admin/products")
    public String createProduct(@ModelAttribute ProductRequestDto requestDto) {
        productService.addProduct(requestDto);
        return "redirect:/admin/products";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        ProductResponseDto product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "admin/edit";
    }

    @PostMapping("/{id}/edit")
    public String updateProduct(@PathVariable Long id,
                                @ModelAttribute ProductRequestDto requestDto) {
        productService.updateProduct(id, requestDto);
        return "redirect:/admin/products";
    }

    @PostMapping("/{id}/delete")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/admin/products";
    }



}
