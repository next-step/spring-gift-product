package gift.controller;

import gift.entity.Product;
import gift.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/products")
public class ProductViewController {
    private final ProductService productService;

    public ProductViewController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String showProductList(Model model) {
        List<Product> products = productService.getAll();
        model.addAttribute("title", "관리자 상품 목록");
        model.addAttribute("products", products);
        return "admin/product-list";
    }

    @GetMapping("/{id}")
    public String showProductDetails(@PathVariable Long id, Model model) {
        Product product = productService.getById(id);

        model.addAttribute("title", "상품 상세 정보");
        model.addAttribute("product", product);
        return "admin/view-product";
    }
}
