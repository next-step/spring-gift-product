package gift.controller;

import gift.dto.ProductRequest;
import gift.dto.ProductResponse;
import gift.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/products")
public class ProductAdminController {

    private final ProductService productService;

    public ProductAdminController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("products", productService.findAll());
        return "products/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("product", new ProductRequest());
        return "products/form";
    }

    @PostMapping
    public String create(@ModelAttribute ProductRequest request) {
        productService.save(request);
        return "redirect:/admin/products";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        ProductResponse product = productService.findById(id);
        ProductRequest form = new ProductRequest(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getImageUrl()
        );
        model.addAttribute("product", form);
        return "products/form";
    }
    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @ModelAttribute ProductRequest request) {
        productService.update(id, request);
        return "redirect:/admin/products";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        productService.delete(id);
        return "redirect:/admin/products";
    }
}
