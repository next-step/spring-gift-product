package gift.controller;

import gift.dto.ProductRequest;
import gift.dto.ProductResponse;
import gift.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/products")
public class AdminController {

    private final ProductService productService;

    public AdminController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String showProductList(Model model, @ModelAttribute ProductRequest productRequest) {
        List<ProductResponse> products = productService.findAllProducts();
        model.addAttribute("products", products);
        return "admin/product-list";
    }

    @GetMapping("/add")
    public String showAddForm(@ModelAttribute ProductRequest productRequest) {
        return "admin/product-form";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute ProductRequest productRequest) {
        productService.addProduct(productRequest);
        return "redirect:/admin/products";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        ProductResponse product = productService.findProductById(id);
        model.addAttribute("productRequest", new ProductRequest(product.name(), product.price(), product.imageUrl()));
        model.addAttribute("productId", id);
        return "admin/product-edit-form";
    }

    @PostMapping("/edit/{id}")
    public String editProduct(@PathVariable("id") Long id, @ModelAttribute ProductRequest productRequest) {
        productService.updateProduct(id, productRequest);
        return "redirect:/admin/products";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return "redirect:/admin/products";
    }

    @PostMapping("/delete")
    public String deleteSelectedProducts(@RequestParam(value = "productIds", required = false) List<Long> productIds) {
        if (productIds != null && !productIds.isEmpty()) {
            productService.deleteProducts(productIds);
        }
        return "redirect:/admin/products";
    }
}