package gift.controller;

import gift.dto.RequestDto;
import gift.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // @RestController는 Json 데이터 반환, @Controller는 html 화면 반환
@RequestMapping("/admin/products")
public class AdminProductController {

    private final ProductService productService;

    public AdminProductController(ProductService productService) {
        this.productService = productService;
    }

    // 1-1. 상품 등록 화면
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new RequestDto());
        return "admin/form";
    }

    // 1-2.상품 등록 처리
    @PostMapping
    public String create(RequestDto dto) {
        productService.create(dto);
        return "admin/products";
    }


}
