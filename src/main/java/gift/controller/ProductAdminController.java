package gift.controller;

import gift.domain.Product;
import gift.dto.ProductRequest;
import gift.dto.ProductResponse;
import gift.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/admin/products")
public class ProductAdminController {

    private final ProductService productService;

    public ProductAdminController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * 상품 목록 (검색)
     */
    @GetMapping
    public String list(
            @RequestParam(defaultValue = "id,asc") String sort,
            @RequestParam(required = false) String keyword,
            Model model) {

        List<Product> filtered = productService.findAllProducts(sort, keyword);
        List<ProductResponse> products = filtered.stream()
                .map(ProductResponse::from)
                .toList();

        model.addAttribute("products", products);
        model.addAttribute("sort", sort);
        model.addAttribute("keyword", keyword);

        return "admin/product/list";
    }



    /**
     * 상품 등록 폼
     */
    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("productRequest", new ProductRequest(null, "", 0, ""));
        return "admin/product/create-product-form";
    }

    /**
     * 상품 등록 처리
     */
    @PostMapping
    public String create(@ModelAttribute ProductRequest request) {
        productService.create(request.name(), request.price(), request.imageUrl());
        return "redirect:/admin/products";
    }

    /**
     * 상품 수정 폼
     */
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Product product = productService.findById(id);
        ProductRequest request = new ProductRequest(
                product.getCategoryId(),
                product.getName(),
                product.getPrice(),
                product.getImageUrl()
        );
        model.addAttribute("productId", product.getId());
        model.addAttribute("productRequest", request);
        return "admin/product/edit-product-form";
    }

    /**
     * 상품 수정 처리
     */
    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id, @ModelAttribute ProductRequest request) {
        productService.update(id, request.name(), request.price(), request.imageUrl());
        return "redirect:/admin/products";
    }

    /**
     * 상품 삭제 처리
     */
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        productService.delete(id);
        return "redirect:/admin/products";
    }

}
