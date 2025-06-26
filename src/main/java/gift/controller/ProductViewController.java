package gift.controller;

import gift.dto.ProductResponseDto;
import gift.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductViewController {

    private final ProductService productService;

    public ProductViewController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/view")
    public String listProducts(Model model, @RequestParam(defaultValue = "1") int page) {

        int pageSize = 5;

        List<ProductResponseDto> allProducts = productService.findAllProducts();

        int totalProducts = allProducts.size();
        int totalPages = (int) Math.ceil((double) totalProducts / pageSize);
        if (totalPages == 0){
            totalPages = 1;
        }

        int fromIndex = (page - 1) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, totalProducts);

        List<ProductResponseDto> products = new ArrayList<>();
        if (fromIndex < totalProducts) {
            products = allProducts.subList(fromIndex, toIndex);
        }

        model.addAttribute("products", products);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "view";
    }
}
