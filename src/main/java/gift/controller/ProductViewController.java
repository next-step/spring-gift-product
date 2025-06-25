package gift.controller;

import gift.dto.FindProductResponseDto;
import gift.service.ProductService;
import java.util.List;
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
    public String showList(Model model) {
        List<FindProductResponseDto> products = productService.findAllProducts();
        model.addAttribute("products", products);
        return "product-list";
    }
}
