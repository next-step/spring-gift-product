package gift.controller;


import gift.dto.request.ProductRequestDto;
import gift.dto.response.ProductResponseDto;
import gift.dto.view.ProductView;
import gift.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/products")
public class ProductAdminController {
    private final ProductService productService;


    public ProductAdminController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String listProducts(Model model){
        List<ProductResponseDto> productDtos = productService.getProducts();

        List<ProductView> productViews = productDtos.stream()
                .map(ProductView::from)
                .toList();

        model.addAttribute("products", productViews);

        return "admin/products";
    }

    @GetMapping("/add")
    public String showAddForm(Model model){
        model.addAttribute("product",
                new ProductRequestDto("", 0L, ""));

        return "admin/add-form";
    }
}
