package gift.controller;

import gift.entity.Product;
import gift.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/products")
public class ProductViewController {

    private final ProductService productService;

    ProductViewController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "admin/product_list";
    }

    @GetMapping("/post")
    public String postProductForm() {
        return "admin/product_post";
    }

    @PostMapping
    public String postProduct(@ModelAttribute Product productWithoutId) {
        productService.createProduct(productWithoutId);
        return "redirect:/admin/products";
    }
}
