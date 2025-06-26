package gift.controller;

import gift.dto.ProductRequestDto;
import gift.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/products")
public class AdminController {

    private final ProductService productService;

    public AdminController(ProductService productService) {
        this.productService = productService;
    }

    // 상품 목록 조회
    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", productService.findAllProducts());
        return "admin/products/list";
    }

    // 상품 단건 조회
    @GetMapping("/{id}")
    public String detailProduct(@PathVariable Long id, Model model) {
        try {
            model.addAttribute("product", productService.findProduct(id));
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", "상품을 찾을 수 없습니다.");
        }
        return "admin/products/detail";
    }

    // 상품 추가
    @GetMapping("/new")
    public String newProduct(Model model) {
        model.addAttribute("product", new ProductRequestDto());
        return "admin/products/new";
    }

    @PostMapping
    public String addProduct(@ModelAttribute ProductRequestDto productRequestDto) {
        productService.saveProduct(productRequestDto);
        return "redirect:/admin/products";
    }

    // 상품 수정
    @GetMapping("/{id}/edit")
    public String editProduct(@PathVariable Long id, Model model) {
        try {
            model.addAttribute("product", productService.findProduct(id));
            model.addAttribute("productId", id);
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", "상품을 찾을 수 없습니다.");
        }
        return "admin/products/edit";
    }

    @PutMapping("/{id}")
    public String updateProduct(@PathVariable Long id,
            @ModelAttribute ProductRequestDto productRequestDto,
            RedirectAttributes redirectAttributes) {
        try {
            productService.updateProduct(id, productRequestDto);
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "상품을 찾을 수 없습니다.");
        }
        return "redirect:/admin/products";
    }

    // 상품 삭제
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id,
            RedirectAttributes redirectAttributes) {
        try {
            productService.deleteProduct(id);
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "상품을 찾을 수 없습니다.");
        }
        return "redirect:/admin/products";
    }

}
