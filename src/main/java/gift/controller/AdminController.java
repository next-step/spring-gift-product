package gift.controller;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;


@Controller
@RequestMapping("/admin/products")
public class AdminController {

    private final ProductService productService;

    public AdminController(ProductService productService) {
        this.productService = productService;
    }

    // 상품 추가 페이지로 이동
    @GetMapping("/new")
    public String goNewProductForm() {
        return "admin/newProductForm";
    }

    // 상품 수정 페이지로 이동
    @GetMapping("/{id}/edit")
    public String editProductForm(@PathVariable Long id, Model model) {

        ProductResponseDto product = productService.findProductById(id);
        model.addAttribute("product", product);

        return "admin/editProductForm";
    }

    // 상품 목록 조회 기능
    @GetMapping
    public String findProducts(
            @RequestParam(value = "id", required = false) Long id,
            Model model)
    {
        if (id == null) {
            List<ProductResponseDto> dtoList = productService.findAllProducts();
            model.addAttribute("products", dtoList);

            return "admin/products";
        }
        
        ProductResponseDto dto = productService.findProductById(id);

        if (dto != null)
            model.addAttribute("products", List.of(dto));
        else
            model.addAttribute("products", Collections.emptyList());

        return "admin/products";
    }

    // 상품 추가 기능 구현
    @PostMapping
    public String saveProduct(@ModelAttribute ProductRequestDto dto) {

        productService.saveProduct(dto);
        return "redirect:/admin/products";
    }

    // 상품 수정 기능 구현
    @PutMapping("{id}")
    public String updateProduct(
            @PathVariable Long id,
            @ModelAttribute ProductRequestDto dto) {

        productService.updateProduct(id, dto);
        return "redirect:/admin/products";
    }

    // 상품 삭제  기능 구현
    @DeleteMapping("{id}")
    public String deleteProduct(@PathVariable Long id) {

        productService.deleteProduct(id);
        return "redirect:/admin/products";
    }
}
