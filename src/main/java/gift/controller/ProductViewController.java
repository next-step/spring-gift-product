package gift.controller;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductViewController {

    private final ProductService productService;

    public ProductViewController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/")
    public String index(Model model) {
        List<ProductResponseDto> products = productService.findAllProducts();
        model.addAttribute("products", products);
        return "index";
    }

    @GetMapping("/products")
    public String productList(Model model) {
        return "redirect:/";
    }

    @GetMapping("/products/new")
    public String moveForm() {
        return "form";
    }

    @PostMapping("/products")
    public String createProduct(@RequestParam String name,@RequestParam int price, @RequestParam String imageUrl) {
        ProductRequestDto dto = new ProductRequestDto(name,price,imageUrl);
        productService.saveProduct(dto);
        return "redirect:/";
    }
}
