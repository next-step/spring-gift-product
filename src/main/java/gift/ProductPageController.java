package gift;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RequestMapping("/products")
@Controller

public class ProductPageController {

    private final ProductStorage products;

    public ProductPageController(ProductStorage products) { this.products = products; }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("products", products.getProducts());
        return "Products";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("product", new Product());
        return "Productform";
    }

    @PostMapping
    public String create(@ModelAttribute Product product) {
        products.save(product);
        return "redirect:/products";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Product product = products.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다. id=" + id));
        model.addAttribute("product", product);
        return "Productform";
    }

    @PostMapping("/{id}")
    public String edit(@PathVariable Long id, @ModelAttribute Product updated) {
        products.update(id, updated);
        return "redirect:/products";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        products.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다. id=" + id));
        products.delete(id);

        return "redirect:/products";
    }

}
