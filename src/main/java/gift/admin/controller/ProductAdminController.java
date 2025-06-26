package gift.admin.controller;

import gift.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/products")
public class ProductAdminController {

    private final ProductService productService;

    @GetMapping
    public String listProducts(Model model) {
        var result = productService.getAllProducts(0, 100, "name", true);
        model.addAttribute("products", result.content());
        return "product-list";
    }


}
