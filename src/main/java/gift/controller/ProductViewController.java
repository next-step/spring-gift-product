package gift.controller;

import gift.dto.ProductResponseDto;
import gift.entity.Product;
import gift.service.ProductService;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductViewController {
    private final ProductService productService;

    public ProductViewController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String showProducts(Model model) {
        Map<Long, Product> productMap = productService.findAllMap();

        model.addAttribute("productMap", productMap); // productMap은 Collections.unmodifiableMap이므로, th:each가 X.
        model.addAttribute("productEntries", productMap.entrySet()); // 이에 따른 entrySet 추가
        return "home";
    }
}
