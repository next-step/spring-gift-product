package gift.Controller;

import gift.domain.Product;
import gift.dto.ProductCreateRequest;
import gift.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Controller
@RequestMapping("/admin/products")
public class AdminProductController {

    private final ProductService productService;

    private static final Logger log = LoggerFactory.getLogger(AdminProductController.class);

    public AdminProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping
    public String list(Model model) {
        List<Product> products = productService.getAll();
        model.addAttribute("products", products);
        return "admin/product-list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Product product = productService.getById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 존재하지 않습니다: "));
        model.addAttribute("product", product);
        return "admin/product-detail";
    }



    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("productCreateRequest", new ProductCreateRequest());
        return "admin/product-form";
    }

    // 상품 등록
    @PostMapping
    public String create(@ModelAttribute("productCreateRequest") ProductCreateRequest request) {
        Product product = request.toEntity();
        productService.create(product);

        return "redirect:/admin/products";
    }

}
