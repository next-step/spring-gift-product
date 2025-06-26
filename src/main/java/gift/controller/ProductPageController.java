package gift.controller;

import gift.domain.Product;
import gift.dto.ProductMapper;
import gift.dto.ProductRequest;
import gift.service.ProductServiceAdminImpl;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/products")
public class ProductPageController {

    private final ProductServiceAdminImpl productServiceAdmin;

    public ProductPageController(ProductServiceAdminImpl productServiceAdmin) {
        this.productServiceAdmin = productServiceAdmin;
    }

    // 상품 목록 조회
    @GetMapping("")
    public String findAll(Model model){
        List<Product> list = productServiceAdmin.getProductListAdmin();
        model.addAttribute("productList", list);
        model.addAttribute("request", new Product());
        return "main";
    }

    // 상품 등록
    @PostMapping("")
    public String createProduct(@ModelAttribute ProductRequest request) {
        Product product = ProductMapper.toEntity(request);
        productServiceAdmin.saveAdmin(product);
        return "redirect:/admin/products";
    }

    // 상품 수정(수정 폼 조회)
    @GetMapping("/update/{productId}")
    public String updateFormProduct(
        @PathVariable Long productId,
        Model model
    ){
        Product product = productServiceAdmin.getProductByIdAdmin(productId);
        ProductRequest request = new ProductRequest(
            product.getId(),
            product.getName(),
            product.getPrice(),
            product.getImageUrl()
        );

        model.addAttribute("request", request);
        return "update";
    }

    // 상품 수정(수정 처리)
    @PostMapping("/update/{productId}")
    public String updateProduct(
        @PathVariable Long productId,
        @ModelAttribute ProductRequest request
    ){
        productServiceAdmin.update(request);

        return "redirect:/admin/products";
    }

    // 상품 삭제
    @GetMapping("/delete/{productId}")
    public String deleteProduct(
        @PathVariable Long productId
    ){
        productServiceAdmin.deleteById(productId);

        return "redirect:/admin/products";
    }
}
