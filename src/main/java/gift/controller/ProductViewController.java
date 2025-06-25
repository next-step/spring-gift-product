package gift.controller;

import gift.dto.ProductRequestDto;
import gift.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductViewController {

    private final ProductService productService;

    public ProductViewController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("products", productService.findAll(0, 100, "id, asc"));
        return "product/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("product", new ProductRequestDto());
        return "product/create";
    }

    @PostMapping
    public String create(@ModelAttribute ProductRequestDto requestDto) {
        productService.create(requestDto);
        return "redirect:/products";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.find(id));
        return "product/detail";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.find(id));
        return "product/edit";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @ModelAttribute ProductRequestDto requestDto) {
        productService.update(id, requestDto);
        return "redirect:/products";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        productService.delete(id);
        return "redirect:/products";
    }
}
