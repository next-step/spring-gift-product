package gift.controller;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import gift.service.ProductService;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/view")
public class ProductViewController {

    private final ProductService productService;

    public ProductViewController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String showProducts(Model model) {
        Map<Long, Product> productMap = productService.findAllMap();

        model.addAttribute("productMap",
            productMap); // productMap은 Collections.unmodifiableMap이므로, th:each가 X.
        model.addAttribute("productEntries", productMap.entrySet()); // 이에 따른 entrySet 추가
        return "home";
    }

    @GetMapping("/create-product.html")
    public String showCreateProductView() {
        return "create-product";
    }

    @GetMapping("/update-product.html")
    public String showUpdateProductView() {
        return "update-product";
    }

    @PostMapping("/products")
    public String createProduct(
        @RequestParam Long id,
        @RequestParam String name,
        @RequestParam int price,
        @RequestParam String imageUrl
    ) {
        productService.createProduct(new ProductRequestDto(id, name, price, imageUrl));

        return "redirect:/view/products";
    }

    @PostMapping("/products/update")
    public String updateProduct(
        @RequestParam Long productId,
        @RequestParam Long id,
        @RequestParam String name,
        @RequestParam int price,
        @RequestParam String imageUrl
    ) {
        productService.updateProduct(productId, new ProductRequestDto(id, name, price, imageUrl));

        return "redirect:/view/products";
    }

    @DeleteMapping("/products/{productId}")
    public String deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);

        return "home";
    }

}
