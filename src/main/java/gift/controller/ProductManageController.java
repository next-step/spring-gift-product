package gift.controller;

import gift.dto.product.CreateProductRequest;
import gift.dto.product.ProductManageResponse;
import gift.dto.product.UpdateProductRequest;
import gift.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/products")
public class ProductManageController {

    private final ProductService productService;

    public ProductManageController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String getProductsForm(Model model) {
        List<ProductManageResponse> products = productService.getAllProducts().stream().map(ProductManageResponse::from).toList();
        model.addAttribute("products", products);
        return "/admin/productList";
    }

    @GetMapping("/new")
    public String createProductForm(Model model) {
        model.addAttribute("request", CreateProductRequest.empty());
        return "/admin/productCreate";
    }


    @PostMapping
    public String createProduct(@ModelAttribute(name = "request") @Valid CreateProductRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/admin/productCreate";
        }
        productService.saveProduct(request);
        return "redirect:/admin/products";
    }

    @GetMapping("/{id}/edit")
    public String updateProductForm(@PathVariable Long id, Model model) {
        ProductManageResponse response = ProductManageResponse.from(productService.getProduct(id));
        model.addAttribute("id", id);
        model.addAttribute("request", UpdateProductRequest.from(response));
        return "/admin/productUpdate";
    }

    @PostMapping("/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute(name = "request") @Valid UpdateProductRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/admin/productUpdate";
        }
        productService.updateProduct(id, request);
        return "redirect:/admin/products";
    }

    @PostMapping("/{id}/delete")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/admin/products";
    }
}
