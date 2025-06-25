package gift.controller;

import gift.dto.product.CreateProductRequest;
import gift.dto.product.ProductManageResponse;
import gift.dto.product.UpdateProductRequest;
import gift.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}/edit")
    public String updateProduct(@PathVariable Long id, Model model) {
        ProductManageResponse product = productService.getProductManagement(id);
        model.addAttribute("id", id);
        model.addAttribute("request", new UpdateProductRequest(product.name(), product.price(), product.quantity()));
        return "/management/productUpdate";
    }

    @PostMapping("/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute UpdateProductRequest request) {
        productService.updateProduct(id, request);
        return "redirect:/management/products";
    }
}
