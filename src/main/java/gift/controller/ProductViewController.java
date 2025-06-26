package gift.controller;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
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

    /**
     * 상품 등록 페이지 View 출력
     * @return Thymeleaf template
     */
    @GetMapping("/create")
    public String createProductView() {
        return "products_create";
    }

    /**
     * 상품 수정 페이지 View 출력
     * @param id 상품 번호
     * @param model 상품Dto 전달할 개체
     * @return Thymeleaf template
     */
    @GetMapping("/patch/{id}")
    public String accessProductView(@PathVariable Long id, Model model) {
        ProductResponseDto responseDto = productService.findProductById(id);
        model.addAttribute("product", responseDto);
        return "products_patch";
    }

    /**
     * 삭제 버튼 구현
     * @param id 상품 번호
     * @return list로 redirect
     */
    @PostMapping("/delete/{id}")
    public String deleteProductView(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/admin/products"; // 목록으로 리다이렉트
    }

    /**
     * 상품 생성 구현
     * @param requestDto 상품 요청
     * @return list로 redirect
     */
    @PostMapping("/create")
    public String createProductView(@ModelAttribute ProductRequestDto requestDto) {
        productService.saveProduct(requestDto);
        return "redirect:/admin/products";
    }


    /**
     * 상품 수정 구현
     * @param id 상품 번호
     * @param requestDto 상품 요청
     * @return list로 redirect
     */
    @PostMapping("/patch/{id}")
    public String patchProductView(@PathVariable Long id, @ModelAttribute ProductRequestDto requestDto) {
        productService.updateProduct(id, requestDto);
        return "redirect:/admin/products";
    }
}
