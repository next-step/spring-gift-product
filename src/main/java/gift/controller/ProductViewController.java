package gift.controller;

import gift.dto.view.ProductViewRequestDto;
import gift.entity.Product;
import gift.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/products")
public class ProductViewController {

    private final ProductService productService;

    public ProductViewController(ProductService productService) {
        this.productService = productService;
    }

    // 상품 목록 화면
    @GetMapping
    public String getProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products/list";     // templates/product/list.html
    }

    // 상품 등록 폼 화면
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("productRequest", new ProductViewRequestDto());
        return "products/form";     // templates/product/form.html
    }

    // 상품 등록 요청 처리
    @PostMapping("/new")
    public String createProduct(@Valid @ModelAttribute("productRequest") ProductViewRequestDto request,
        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "products/form";     // 유효성 오류 있으면 다시 폼으로
        }

        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setImageUrl(request.getImageUrl());

        productService.registerProduct(product);
        return "redirect:/admin/products";
    }

}
