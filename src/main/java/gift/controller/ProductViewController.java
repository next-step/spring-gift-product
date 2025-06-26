package gift.controller;

import gift.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/admin")
public class ProductViewController {
    // 의존성 고정하여 안전하게 유지
    private final ProductService productService;

    // 의존성 주입
    private ProductViewController(ProductService productService) {this.productService = productService;}

    /**
     * 관리자 페이지 제품 리스트 View 출력
     * @param model 렌더링용 모델 객체
     * @return Thymeleaf template
     */

    @GetMapping("/products")
    public String productsListView(Model model) {
        model.addAttribute("products", productService.findAllProduct());
        return "products_list";
    }

    @PostMapping("/delete/{id}")
    public String deleteProductView(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/admin/products"; // 목록으로 리다이렉트
    }
}
