package gift.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @GetMapping("/admin/products")
    public String productAdmin() {
        return "admin/products";  // templates/admin/products.html
    }
}
