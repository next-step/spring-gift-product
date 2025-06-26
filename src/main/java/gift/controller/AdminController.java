package gift.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/products")
    public String productManageView() {
        return "admin/product-manage";
    }

    @GetMapping("/products/create")
    public String productCreateView() {
        return "admin/product-create";
    }
}