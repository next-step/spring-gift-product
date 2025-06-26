package gift.controller;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/products")
public class AdminProductController {

    private final ProductService productService;

    public AdminProductController(ProductService productService) {
        this.productService = productService;
    }

    //목록 화면
    @GetMapping
    public String list(@RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "10") int size,
                       Model model) {

        // 서비스 호출
        List<ProductResponseDto> products = productService.getProductList(page, size);

        // 총 페이지 계산용
        int total = productService.getProductList(0, Integer.MAX_VALUE).size();
        int totalPages = (int) Math.ceil((double) total / size);

        // 뷰로 전달
        model.addAttribute("products", products);
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        model.addAttribute("totalPages", totalPages);

        return "admin/products";            // templates/admin/products.html
    }

    //등록 화면
    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("form", new ProductRequestDto());
        return "admin/product-form";
    }

    @PostMapping("/new")
    public String createProduct(@ModelAttribute("form") ProductRequestDto requestDto,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/product-form";
        }

        productService.createProduct(requestDto);
        return "redirect:/admin/products";
    }


    //수정 화면
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        ProductResponseDto responseDto = productService.getProduct(id);

        ProductRequestDto requestDto = new ProductRequestDto();
        requestDto.setName(responseDto.getName());
        requestDto.setPrice(responseDto.getPrice());
        requestDto.setImageUrl(responseDto.getImageUrl());

        model.addAttribute("form", requestDto);
        model.addAttribute("productId", id);
        return "admin/product-form";
    }

    @PostMapping("/{id}/edit")
    public String updateProduct(@PathVariable Long id,
                                @ModelAttribute("form") ProductRequestDto requestDto) {
        productService.updateProduct(id, requestDto);
        return "redirect:/admin/products";
    }

    @PostMapping("/{id}/delete")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/admin/products";
    }

}
