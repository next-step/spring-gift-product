package gift.front.controller;

import gift.api.dto.ProductRequestDto;
import gift.api.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/products")
public class AdminController {

    private final ProductService productService;

    public AdminController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String allProducts(
            @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
            @RequestParam(required = false) Long categoryId,
            Model model
    ) {
        model.addAttribute("products", productService.findAllProducts(pageable, categoryId));
        model.addAttribute("page", pageable.getPageNumber());

        return "admin/product-list";
    }

    @GetMapping("/{id}")
    public String productDetail(
            @PathVariable Long id, Model model) {
        model.addAttribute("product", productService.findProductById(id));

        return "admin/product-detail";
    }

    @GetMapping("/{id}/edit")
    public String editProduct(
            @PathVariable Long id, Model model) {
        model.addAttribute("product", productService.findProductById(id));

        return "admin/product-edit";
    }

    @GetMapping("/new")
    public String newProduct(Model model) {
        model.addAttribute("product", new ProductRequestDto(null, null, null));

        return "admin/product-new";
    }

    @PostMapping
    public String createProduct(
            @Valid @ModelAttribute("product") ProductRequestDto productRequestDto,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("product", productRequestDto);
            return "admin/product-new";
        }

        productService.createProduct(productRequestDto);

        return "redirect:/admin/products";
    }

    @PutMapping("/{id}")
    public String updateProduct(
            @PathVariable Long id,
            @Valid @ModelAttribute("product") ProductRequestDto productRequestDto,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("id", id);
            model.addAttribute("product", productRequestDto);
            return "admin/product-edit";
        }

        productService.updateProduct(id, productRequestDto);

        return "redirect:/admin/products/" + id;
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);

        return "redirect:/admin/products";
    }
}
