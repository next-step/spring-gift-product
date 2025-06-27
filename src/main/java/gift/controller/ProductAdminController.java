package gift.controller;

import gift.dto.ProductRequestDto;
import gift.entity.Product;
import gift.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/products")
public class ProductAdminController {

    private final ProductService service;

    public ProductAdminController(ProductService service) {
        this.service = service;
    }

    // 전체 목록 조회
    @GetMapping
    public String list(Model model) {
        model.addAttribute("products", service.getAll());
        return "admin/list";  // templates/admin/list.html
    }

    // 추가 폼
    @GetMapping("/create")
    public String createForm() {
        return "admin/create";  // templates/admin/create.html
    }

    // 상품 추가
    @PostMapping("/create")
    public String create(ProductRequestDto dto) {
        service.createProduct(dto);
        return "redirect:/admin/products";
    }

    // 수정 폼
    @GetMapping("/{id}/update")
    public String updateForm(@PathVariable Long id, Model model) {
        Product product = service.getById(id);
        model.addAttribute("product", product);
        return "admin/update";  // templates/admin/update.html
    }

    // 상품 수정
    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id, ProductRequestDto dto) {
        service.updateProduct(id, dto);
        return "redirect:/admin/products";
    }

    // 상품 삭제
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/admin/products";
    }
}