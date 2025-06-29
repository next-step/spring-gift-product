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
    private final String BASE_URL = "https://media.istockphoto.com/id/1667499762/ko/%EB%B2%A1%ED%84%B0/%EC%98%81%EC%97%85%EC%A4%91-%ED%8C%90%EC%A7%80-%EC%83%81%EC%9E%90.jpg?s=612x612&w=0&k=20&c=94uRFQLclgFtnDhE4OfO1tCJdETL3uuBM9ZHD_N4P4Y=";
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
        if(product.getImageUrl() == null || product.getImageUrl().isEmpty()) {
            product.setImageUrl(BASE_URL);
        }
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
        oldProduct.setImageUrl(product.getImageUrl());
        return "redirect:/admin/products";
    }

    @PostMapping("/{id}/delete")
    public String deleteProduct(@PathVariable Long id) {
        products.remove(id);
        return "redirect:/admin/products";

    }

}