package gift.controller;

import gift.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products/management")
public class ProductManagementViewController {

    private final ProductService productService;

    public ProductManagementViewController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("products",productService.getAllProduct());
        return "management/home";
    }

    @GetMapping("/create")
    public String create(Model model) {
        //
        return "management/create";
    }

    /*@GetMapping("/{id}")
    public String home(Model model) {

    }*/
}
