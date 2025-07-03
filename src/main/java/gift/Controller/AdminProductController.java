package gift.controller;

import gift.entity.Product;
import gift.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/products")
public class AdminProductController {

    private final ProductRepository repository;

    public AdminProductController(ProductRepository repository) {

        this.repository = repository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("products", repository.findAll());
        return "main";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        return "create";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute Product product) {
        repository.create(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Product product = repository.findById(id).orElse(null);
        if (product == null) {
            return "redirect:/admin/products";
        }
        model.addAttribute("product", product);
        return "create";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Product product) {
        product.setId(id);
        repository.update(product);
        return "redirect:/admin/products";
    }

    @PostMapping("/{id}")
    public String delete(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/admin/products";
    }
}
