package gift.controller;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/products")
public class AdminProductController {

    private final ProductService productService;

    public AdminProductController(ProductService productService) {
        this.productService = productService;
    }

    // 목록 출력
    @GetMapping
    public String showProductManagePage(Model model) {
        List<ProductResponseDto> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "admin/index";
    }

    // 상품 추가 처리
    @PostMapping
    public String addProduct(@ModelAttribute ProductRequestDto productRequestDto) {
        productService.addProduct(productRequestDto);
        return "redirect:/admin/products";
    }

    // 수정 처리
    @PutMapping("/{id}")
    public String updateProduct(
            @PathVariable Long id,
            ProductRequestDto requestDto
    ) {
        productService.updateProduct(id, requestDto);
        // 수정 후 관리자 목록 페이지로 리다이렉트
        return "redirect:/admin/products";
    }

    // 삭제 처리
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/admin/products";
    }
}
