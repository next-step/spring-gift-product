package gift.product.controller.view;

import gift.product.domain.Product;
import gift.product.dto.ProductDto;
import gift.product.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/api/admin")
public class ProductAdminController {
    private final ProductService productService;

    public ProductAdminController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/list")
    public String findAll(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/product/add")
    public String addForm(Model model) {
        model.addAttribute("productdto", new ProductDto());
        return "addForm";
    }

    @PostMapping("/product/add")
    public String saveProduct(@ModelAttribute ProductDto productdto) {
        productService.saveProduct(productdto);
        return "redirect:/api/admin/product/list";
    }

    @ResponseBody
    @GetMapping("/product/{id}")
    public Product findById(@PathVariable String id) {
        return productService.findById(id);
    }

    @GetMapping("/product/{id}/update")
    public String updateForm(@PathVariable String id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "updateForm";
    }

    @PatchMapping("/product/{id}/update")
    public String updateProduct(@PathVariable String id, @ModelAttribute ProductDto updateProductdto) {
        productService.updateProduct(id, updateProductdto);
        return "redirect:/api/admin/product/list";
    }

    @DeleteMapping("/product/{id}/delete")
    public String deleteById(@PathVariable String id) {
        Product product = productService.deleteProduct(id);
        return "redirect:/api/admin/product/list";
    }
}
