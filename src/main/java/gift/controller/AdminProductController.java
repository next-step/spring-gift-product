package gift.controller;

import gift.domain.Product;
import gift.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/products")
@RequiredArgsConstructor
public class AdminProductController {

    private final ProductService productService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("products", productService.findAll());
        return "product/list";        // templates/product/list.html
    }


    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("product", new Product(null, "", 0, ""));
        return "product/form";
    }


    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        productService.findById(id).ifPresent(p -> model.addAttribute("product", p));
        return "product/form";
    }

    @PostMapping
    public String save(@ModelAttribute @Validated Product product) {
        if (product.getId() == null) {
            productService.save(product);
        } else {
            productService.update(product.getId(), product);
        }
        return "redirect:/admin/products";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        productService.delete(id);
        return "redirect:/admin/products";
    }
}
