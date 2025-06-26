package gift.controller;

import gift.dto.RequestDto;
import gift.dto.ResponseDto;
import gift.service.ProductService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

    // 2. 상품 목록
    @GetMapping
    public String list(Model model) {
        List<ResponseDto> products = productService.findAll();
        model.addAttribute("products", products);
        return "admin/list";
    }

    // 3-1. 상품 수정 폼
    @GetMapping("{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        ResponseDto product = productService.findById(id);
        model.addAttribute("product", product);
        return "admin/form";
    }

    // 3-2. 상품 수정 처리
    @PostMapping
    public String update(@PathVariable Long id, @ModelAttribute RequestDto dto) {
        productService.update(id, dto);
        return "admin/products";
    }
}
