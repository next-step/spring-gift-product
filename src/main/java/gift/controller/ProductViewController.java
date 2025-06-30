package gift.controller;

import gift.dto.PageRequestDto;
import gift.dto.PageResult;
import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/products")
public class ProductViewController {

    private final ProductService productService;

    public ProductViewController(ProductService productService) {
        this.productService = productService;
    }

    // 상품 등록
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("product", new ProductRequestDto("하리보 콜라맛", 2000, "test.jpg"));
        return "product/create";
    }

    // 상품 등록 처리
    @PostMapping
    public String create(@ModelAttribute @Valid ProductRequestDto dto) {
        productService.createProduct(dto);
        return "redirect:/admin/products";
    }

    @GetMapping
    public String list(@RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "5") int size,
                       Model model) {

        PageRequestDto pageRequestDto = new PageRequestDto(page, size);
        PageResult<ProductResponseDto> pageResult = productService.findAllProducts(pageRequestDto);

        model.addAttribute("pageResult", pageResult);
        return "product/list";
    }

    @GetMapping("/{id}")
    public String getProduct(@PathVariable Long id, Model model) {
        ProductResponseDto product = productService.findProductById(id);
        model.addAttribute("product", product);
        return "product/detail";
    }

    @GetMapping("/{id}/update")
    public String editForm(@PathVariable Long id, Model model) {
        ProductResponseDto product = productService.findProductById(id);
        model.addAttribute("product", product);
        return "product/update";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @ModelAttribute @Valid ProductRequestDto dto) {
        productService.updateProduct(id, dto);
        return "redirect:/admin/products";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/admin/products";
    }

}
