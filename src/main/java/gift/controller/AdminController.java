package gift.controller;

import gift.dto.CreateProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/products")
public class AdminController {
    private final ProductService productService;

    public AdminController(ProductService productService) {
        this.productService = productService;
    }

    // 상품 목록 조회
    @GetMapping
    public String list(Model model) {
        List<ProductResponseDto> products = productService.findAllProducts();
        model.addAttribute("products", products);

        return "admin/product/list";
    }

    // 상품 단건 조회
    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        ProductResponseDto findProduct = productService.findProductById(id);
        model.addAttribute("product", findProduct);

        return "admin/product/detail";
    }

    // 상품 등록 페이지
    @GetMapping("/new")
    public String createProduct(Model model) {
        model.addAttribute("product", new CreateProductRequestDto("", 0L, ""));

        return "admin/product/create";
    }

    // 상품 등록
    @PostMapping("/new")
    public String createProduct(@Valid @ModelAttribute("product") CreateProductRequestDto requestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            return "admin/product/create";
        }
        productService.createProduct(requestDto.name(), requestDto.price(), requestDto.imageUrl());

        return "redirect:/admin/products";
    }

    // 상품 삭제
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);

        return "redirect:/admin/products";
    }
}
