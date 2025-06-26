package gift.controller;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductViewController {

    private final ProductService productService;

    public ProductViewController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/view")
    public String showProductsForm(Model model, @RequestParam(defaultValue = "1") int page) {

        int pageSize = 5;

        List<ProductResponseDto> allProducts = productService.findAllProducts();

        int totalProducts = allProducts.size();
        int totalPages = (int) Math.ceil((double) totalProducts / pageSize);
        if (totalPages == 0){
            totalPages = 1;
        }

        int fromIndex = (page - 1) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, totalProducts);

        List<ProductResponseDto> products = new ArrayList<>();
        if (fromIndex < totalProducts) {
            products = allProducts.subList(fromIndex, toIndex);
        }

        model.addAttribute("products", products);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "view";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("product", new ProductRequestDto());
        return "add";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute ProductRequestDto requestDto) {
        productService.addProduct(requestDto);
        return "redirect:/products/add";
    }

    @GetMapping("/{id}")
    public String showProductForm(@PathVariable Long id, Model model) {
        return productService.findProductById(id)
                .map(product -> {
                    model.addAttribute("product", product);
                    return "detail";
                })
                .orElse("not-found");
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        return productService.findProductById(id)
                .map(product -> {
                    model.addAttribute("product", product);
                    return "edit";
                })
                .orElse("not-found");
    }

    @PostMapping("/edit/{id}")
    public String editProduct(@PathVariable Long id, @ModelAttribute ProductRequestDto requestDto) {
        productService.updateProduct(id, requestDto);
        return "redirect:/products/" + id;
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products/view";
    }

}
