package gift.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    /**
     * Method Description
     * - /admin/products 경로로의 GET 요청을 처리
     * - 'products' 이름의 뷰 (Thymeleaf 템플릿)를 렌더링
     */
    @GetMapping("/products")
    public String productsPage() {
        return "products"; // 위치 : resources/templates/products.html
    }
}