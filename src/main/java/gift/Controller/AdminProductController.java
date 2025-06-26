package gift.Controller;

import gift.Entity.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/admin/products")
public class AdminProductController {

    private final Map<Long, Product> productMap = new LinkedHashMap<>();
    private long nextId = 1;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("products", productMap.values());
        return "product/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        return "product/form";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute Product product) {
        product.setId(nextId++);
        productMap.put(product.getId(), product);
        return "redirect:/admin/products";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Product product = productMap.get(id);
        if (product == null) {
            return "redirect:/admin/products";
        }
        model.addAttribute("product", product);
        return "product/form";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Product product) {
        product.setId(id);
        productMap.put(id, product);
        return "redirect:/admin/products";
    }

    @PostMapping("/{id}")
    public String delete(@PathVariable Long id) {
        productMap.remove(id);
        return "redirect:/admin/products";
    }
}
