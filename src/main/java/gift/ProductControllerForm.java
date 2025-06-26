package gift;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Controller
@RequestMapping("/products")

public class ProductControllerForm {

    private final Map<Long, Product> products = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @GetMapping
    public String list(Model model) {
        model.addAttribute("products", products.values());
        return "Products";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("product", new Product());
        return "Productform";
    }

    @PostMapping
    public String create(@ModelAttribute Product product) {
        product.setId(idGenerator.getAndIncrement());
        products.put(product.getId(), product);
        return "redirect:/products";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Product product = products.get(id);
        model.addAttribute("product", product);
        return "Productform";
    }

    @PostMapping("/{id}")
    public String edit(@PathVariable Long id, @ModelAttribute Product updated) {
        updated.setId(id);
        products.put(id, updated);
        return "redirect:/products";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        products.remove(id);
        return "redirect:/products";
    }

}
