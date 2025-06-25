package gift.controller;

import gift.dto.product.CreateProductRequest;
import gift.dto.product.ProductManageResponse;
import gift.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/management/products")
public class ProductManageController {

    private final ProductService productService;

    public ProductManageController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String getProducts(Model model) {
        List<ProductManageResponse> products = productService.getAllProductsManagement();
        model.addAttribute("products", products);
        return "/management/productList";
    }

    @GetMapping("/new")
    public String createProduct(Model model) {
        model.addAttribute("request", new CreateProductRequest(null, null, null));
        return "/management/productCreate";
    }


    @PostMapping
    public String createProduct(@ModelAttribute CreateProductRequest request) {
        productService.saveProduct(request);
        return "redirect:/management/products";
    }
}
