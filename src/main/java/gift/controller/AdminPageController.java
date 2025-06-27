package gift.controller;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import gift.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/products")
public class AdminPageController {

    private final ProductService productService;

    public AdminPageController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String getProducts(Model model) {
        List<Product> products = productService.getProductList();
        List<ProductResponseDto> response = products.stream()
                .map(product -> ProductResponseDto.from(product))
                .toList();
        model.addAttribute("products", response);
        return "admin/product-list";
    }

    // 신규상품 등록 form 제공
    @GetMapping("/new")
    public String newProduct(Model model) {
        model.addAttribute("productId", null);
        model.addAttribute("product", ProductRequestDto.empty());
        return "admin/product-form";
    }

    // 신규상품 등록 form 받고, 검증 및 redirection 수행
    @PostMapping
    public String newProduct(
            @Valid @ModelAttribute ProductRequestDto request,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("productId", null);
            model.addAttribute("product", request);
            return "admin/product-form";
        }
        Product created = productService.createProduct(
                request.name(),
                request.price(),
                request.imageUrl()
        );
        redirectAttributes.addFlashAttribute("message", "Product created");
        return "redirect:/admin/products/" + created.getId();
    }

    @GetMapping("/{id}")
    public String getProduct(
            @PathVariable Long id,
            Model model
    ) {
        ProductRequestDto dto =
                ProductRequestDto.from(productService.getProductById(id));
        model.addAttribute("productId", id);
        model.addAttribute("product", dto);
        return "admin/product-form";
    }

    @PutMapping("/{id}")
    public String updateProduct(
            @PathVariable Long id,
            @Valid @ModelAttribute ProductRequestDto request,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            return "redirect:/admin/products/" + id;
            model.addAttribute("productId", id);
            model.addAttribute("product", request);
        }
        Product updated = productService.putProductById(
                id,
                request.name(),
                request.price(),
                request.imageUrl()
        );
        redirectAttributes.addFlashAttribute("message", "Product updated");
        return "redirect:/admin/products/" + updated.getId();
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes
    ) {
        productService.deleteProductById(id);
        redirectAttributes.addFlashAttribute("message", "Product deleted");
        return "redirect:/admin/products";
    }

}
