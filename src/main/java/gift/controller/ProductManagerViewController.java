package gift.controller;

import gift.dto.request.ProductRequestDto;
import gift.dto.request.ProductUpdateRequestDto;
import gift.entity.Product;
import gift.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductManagerViewController {

    private final ProductService productService;

    public ProductManagerViewController(ProductService productService) {
        this.productService = productService;
    }

    //상품 조회
    @GetMapping("/home")
    public String managerHome(Model model) {
        return "products";
    }

    //상품 단건 조회
    @GetMapping("/home/{productId}")
    public String getProductById(@PathVariable long productId, Model model,
        RedirectAttributes redirectAttributes) {
        Product product = productService.getProduct(productId);
        if (product == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "상품이 존재하지 않습니다.");
            return "redirect:/home";
        }
        model.addAttribute("product", product);
        return "product"; // product.html로 이동
    }

    //상품 추가
    @PostMapping("/home")
    public String createProduct(
        @ModelAttribute ProductRequestDto productRequestDto) {
        productService.createProduct(productRequestDto);
        return "redirect:/home";
    }


    //상품 수정(수정 페이지 이동)
    @PostMapping("/home/{productId}")
    public String updateProduct(
        @PathVariable long productId,
        @ModelAttribute ProductUpdateRequestDto productUpdateRequestDto,
        RedirectAttributes redirectAttributes) {
        if (!productService.containsProduct(productId)) {
            redirectAttributes.addFlashAttribute("errorMessage", "상품이 존재하지 않음");
            return "redirect:/home";
        }
        productService.updateProduct(productId, productUpdateRequestDto);
        return "redirect:/home";
    }

    //상품 삭제
    @PostMapping("/home/delete")
    public String deleteProduct(@RequestParam long productId,
        RedirectAttributes redirectAttributes) {
        if (!productService.containsProduct(productId)) {
            redirectAttributes.addFlashAttribute("errorMessage", "상품이 존재하지 않음");
            return "redirect:/home";
        }
        productService.deleteProduct(productId);
        return "redirect:/home";
    }
}
