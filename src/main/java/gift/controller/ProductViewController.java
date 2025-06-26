package gift.controller;

import gift.dto.ProductRequestDto;
import gift.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/products")
public class ProductViewController {

    private final ProductService productService;

    public ProductViewController(ProductService productService) {
        this.productService = productService;
    }

    // 상품 등록 페이지
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("product", new ProductRequestDto());
        System.out.println(
                "hdy"
        );
        return "product/create";
    }

    // 상품 등록 처리
    @PostMapping
    public String create(@ModelAttribute ProductRequestDto dto) {
        productService.createProduct(dto);
        return "redirect:/products";
    }
}
