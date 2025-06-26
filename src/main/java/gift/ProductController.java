package gift;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Controller
@RequestMapping("/admin/products")

public class ProductController {

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
        return "redirect:/admin/products";
    }

}
