package gift.controller;

import gift.dto.AddProductRequestDto;
import gift.dto.FindProductResponseDto;
import gift.service.ProductService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductViewController {
    
    private final ProductService productService;
    
    public ProductViewController(ProductService productService) {
        this.productService = productService;
    }
    
    @GetMapping
    public String showListView(Model model) {
        List<FindProductResponseDto> products = productService.findAllProducts();
        model.addAttribute("products", products);
        return "product-list";
    }
    
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("productForm", new AddProductRequestDto());
        return "product-add";
    }
    
    @PostMapping("/add")
    public String addProduct(@ModelAttribute AddProductRequestDto productForm) {
        productService.addProduct(productForm);
        return "redirect:/products";
    }
}
