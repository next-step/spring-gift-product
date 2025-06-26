package gift.controller;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/products")
public class ProductViewController {

    private final ProductService productService;

    public ProductViewController(ProductService productService) {
        this.productService = productService;
    }

    // 상품 등록
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("product", new ProductRequestDto());
        return "product/create";
    }

    // 상품 등록 처리
    @PostMapping
    public String create(@ModelAttribute ProductRequestDto dto) {
        productService.createProduct(dto);
        return "redirect:/admin/products";
    }

    @GetMapping
    public String list(@PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                       Model model) {
        Page<ProductResponseDto> products = productService.findAllProducts(pageable);

        model.addAttribute("products", products);
        model.addAttribute("currentPage", products.getNumber());
        model.addAttribute("totalPages", products.getTotalPages());

        return "product/list";
    }

    @GetMapping("/{id}")
    public String getProduct(@PathVariable Long id, Model model) {
        ProductResponseDto product = productService.findProductById(id);
        model.addAttribute("product", product);
        return "product/detail";
    }
}
