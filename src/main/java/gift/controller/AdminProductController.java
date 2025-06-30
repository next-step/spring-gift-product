package gift.controller;

import gift.domain.Product;
import gift.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@Controller
@RequestMapping("/admin/products")

public class AdminProductController {
    //
    private final ProductService productService;

    public AdminProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("products", productService.findAll());
        return "product/list";        // templates/product/list.html
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("product", new Product());   // ← 변경
        return "product/form";
    }


    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Product product = productService.findById(id)
                .orElseThrow(() -> new NoSuchElementException("상품을 찾을 수 없습니다."));
        model.addAttribute("product", product);
        return "product/form";
    }

    @PostMapping
    public String save(@ModelAttribute @Validated Product product) {
        productService.save(product);
        return "redirect:/admin/products";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @ModelAttribute @Validated Product product) {
        productService.update(id, product);
        return "redirect:/admin/products";
    }



    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        productService.delete(id);
        return "redirect:/admin/products";
    }
}
