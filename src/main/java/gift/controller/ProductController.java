package gift.controller;


import gift.domain.Product;
import gift.dto.CreateProductRequest;
import gift.service.ProductService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public String items(Model model) {
        List<Product> products = service.findAll();
        model.addAttribute("products", products);
        return "/products";
    }

    @GetMapping("/add")
    public String addProduct() {
        return "/addForm";
    }


    @PostMapping("/add")
    public String addProduct(@ModelAttribute CreateProductRequest request, RedirectAttributes redirectAttributes) {
        service.save(request);
        return "redirect:/api/products";
    }
}
