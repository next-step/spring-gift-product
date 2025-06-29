package gift.controller;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final ProductService productService;

    public AdminController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String findAll(Model model) {
        List<ProductResponseDto> ProductResponseDtoList = productService.findAllProducts();
        model.addAttribute("products", ProductResponseDtoList);
        return "admin/product-list";
    }

    @GetMapping("/products-add")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new ProductRequestDto(null, null, null));
        return "admin/product-add";
    }

    @PostMapping("/products-add")
    public String createProduct(@Valid @ModelAttribute("product") ProductRequestDto dto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("product", dto);
            return "admin/product-add";
        }
        productService.saveProduct(dto);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        ProductResponseDto product = productService.findProductById(id);
        model.addAttribute("product", product);
        return "admin/product-edit";
    }

    @PostMapping("/{id}")
    public String updateProduct(@PathVariable Long id, @Valid @ModelAttribute("product") ProductRequestDto dto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("product", dto);
            return "admin/product-edit";
        }
        productService.updateProduct(id, dto);
        return "redirect:/admin";
    }

    @PostMapping("/{id}/delete")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/admin";
    }

}