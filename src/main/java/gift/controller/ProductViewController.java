package gift.controller;

import gift.dto.ProductRequestDTO;
import gift.dto.ProductResponseDTO;
import gift.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductViewController {
    private final ProductService productService;
    public ProductViewController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String getAllProducts(Model model) {
        List<ProductResponseDTO> products = productService.getAll();
        model.addAttribute("products", products);
        return "products";
    }

    @PostMapping("/edit")
    public String updateProduct(@ModelAttribute ProductRequestDTO product) {
        productService.update(product.getId(), product);
        return "redirect:/products";
    }
}
