package gift.admin.controller;

import gift.product.dto.CreateProductReqDto;
import gift.product.dto.UpdateProductReqDto;
import gift.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/products")
public class ProductAdminController {

    private final ProductService productService;

    @GetMapping
    public String listProducts(Model model) {
        var result = productService.getAllProducts(0, 100, "name", true);
        model.addAttribute("products", result.content());
        return "product-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("product", new CreateProductReqDto(null, null, null));
        return "product-add";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute CreateProductReqDto dto) {
        productService.createProduct(dto);
        return "redirect:/admin/products";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        var product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "product-edit";
    }

    @PostMapping("/edit/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute UpdateProductReqDto dto) {
        productService.updateProduct(id, dto);
        return "redirect:/admin/products";
    }


}
