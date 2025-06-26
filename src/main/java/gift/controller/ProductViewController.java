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

    @GetMapping("/products/{id}")
    public String findProduct(@PathVariable Long id, Model model) {
        ProductResponseDto dto = productService.findProductById(id);
        model.addAttribute("product", dto);
        return "detail";
    }
    @GetMapping("/products/{id}/update")
    public String moveUpdateForm(@PathVariable Long id, Model model) {
        ProductResponseDto product = productService.findProductById(id);
        model.addAttribute("product", product);
        return "update";
    }
    @PostMapping("/products/{id}")
    public String updateProduct(@PathVariable Long id,@RequestParam String name,@RequestParam int price, @RequestParam String imageUrl) {
        ProductRequestDto dto = new ProductRequestDto(name, price, imageUrl);
        productService.updateProduct(id, dto);
        return "redirect:/products/" + id;
    }

    @GetMapping("/products/{id}/delete")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/";
    }
}
