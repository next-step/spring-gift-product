package gift.controller;

import gift.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;


@Controller
@RequestMapping("/admin/products")
public class ProductController {

    private final ProductService productService;

    //생성자 주입
    public ProductController(ProductService productService){
        this.productService=productService;
    }

    @GetMapping
    public String list(Model model){
        model.addAttribute("products",productService.findAll());
        return "product/list";
    }

}
