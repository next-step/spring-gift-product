package gift.controller;


import gift.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/admin/products")
public class AdminProductController {
    private final ProductService productService;
    public AdminProductController(ProductService productService)
    {
        this.productService = productService;
    }

    //list 목록 보기
    @GetMapping
    public String list(Model model)
    {
        model.addAttribute("products",productService.getAll());
        return "admin/products/list";
    }

}
