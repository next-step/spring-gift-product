package gift.controller;

import gift.dto.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Controller
@RequestMapping("/admin/products")

public class ProductController {
    private final Map<Long, Product> products = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    @GetMapping // 전체 상품 조회 API
    public String getProducts(Model model) {
        Collection<Product> list = products.values();
        model.addAttribute("products", list);
        return "admin/product_list";
    }
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("product",new Product());
        model.addAttribute("editMode",false);
        return "admin/product_form";
    }

    @PostMapping
    public String createProduct(@ModelAttribute Product product) {
        long id = idGenerator.incrementAndGet();
        product.setId(id);
        products.put(id, product);
        return "redirect:/admin/products";
    }

    @GetMapping("/{id}/edit")
    public String editProduct(@PathVariable Long id, Model model) {
        Product product = products.get(id);
        model.addAttribute("product", product);
        model.addAttribute("editMode", true);
        return "/admin/product_form";
    }

    @PostMapping("/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute Product product) {
        Product oldProduct = products.get(id);
        oldProduct.setName(product.getName());
        oldProduct.setPrice(product.getPrice());
        return "redirect:/admin/products";
    }

    @PostMapping("/{id}/delete")
    public String deleteProduct(@PathVariable Long id) {
        products.remove(id);
        return "redirect:/admin/products";

    }

}