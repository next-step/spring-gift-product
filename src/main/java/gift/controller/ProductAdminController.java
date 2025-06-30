package gift.controller;

import gift.model.Product;
import gift.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/products")
public class ProductAdminController {

    private final ProductService productService;

    public ProductAdminController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String showProductList(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "product/list"; // templates/product/list.html
    }
    
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product());
        return "product/create-form";
    }

    @PostMapping
    public String addProduct(@ModelAttribute Product product) {
        productService.addProduct(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getProduct(id));
        return "product/edit-form";
    }

    @PostMapping("/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute Product product) {
        productService.updateProduct(id, product);
        return "redirect:/admin/products";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/admin/products";
    }

}
