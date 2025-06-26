package gift.controller;

import gift.dto.request.ProductRequestDto;
import gift.entity.Product;
import gift.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Controller
@RequestMapping("/admin/products")
public class AdminProductController {
    private final ProductService productService;

    public AdminProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping
    public String showProductList(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "admin/product-list";
    }


    /// /////////////등록///////////////

    @GetMapping("/new")
    public String  showCreateProductForm(Model model) {
        model.addAttribute("product", new ProductRequestDto());
        return "admin/product-form";
    }

    @PostMapping
    public String addProduct(@ModelAttribute ProductRequestDto productRequestDto) {
        productService.createProduct(productRequestDto);
        return "redirect:/admin/products";
    }




}
