package gift.admin.controller;

import gift.exception.EntityNotFoundException;
import gift.product.dto.ProductCreateRequestDto;
import gift.product.dto.ProductUpdateRequestDto;
import gift.product.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ProductService productService;

    public AdminController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String adminDashboard(Model model) {
        var products = productService.getProducts();
        model.addAttribute("products", products);
        model.addAttribute("productCreateRequestDto", new ProductCreateRequestDto("", 0L, ""));
        return "admin/dashboard";
    }

    @PostMapping("/products/new")
    public String createProduct(@Valid @ModelAttribute ProductCreateRequestDto product,
            BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", "입력값을 확인해주세요.");
            return "redirect:/admin";
        }

        productService.createProduct(product);
        redirectAttributes.addFlashAttribute("message", "상품이 등록되었습니다.");
        return "redirect:/admin";
    }

    @PostMapping("/products/{id}/edit")
    public String updateProduct(@PathVariable Long id,
            @Valid @ModelAttribute ProductUpdateRequestDto product, BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", "입력값을 확인해주세요.");
            return "redirect:/admin";
        }

        productService.updateProduct(id, product);
        redirectAttributes.addFlashAttribute("message", "상품이 수정되었습니다.");
        return "redirect:/admin";
    }

    @PostMapping("/products/{id}/delete")
    public String deleteProduct(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            productService.deleteProduct(id);
            redirectAttributes.addFlashAttribute("message", "상품이 삭제되었습니다.");
        } catch (EntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("error", "삭제 실패: " + e.getMessage());
        }
        return "redirect:/admin";
    }
}
