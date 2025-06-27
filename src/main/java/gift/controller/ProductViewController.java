package gift.controller;

import gift.dto.request.ProductCreateRequestDto;
import gift.dto.response.ProductGetResponseDto;
import gift.service.ProductService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/admin/products")
public class ProductViewController {

    private final ProductService productService;

    public ProductViewController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/create-product")
    public String createProductPage() {
        return "create-product";
    }

    @PostMapping("/create-product")
    public String createProduct(
        @RequestParam String name,
        @RequestParam Double price,
        @RequestParam String imageUrl
    ) {

        try {
            ProductCreateRequestDto productCreaterequestDto = new ProductCreateRequestDto(name,
                price, imageUrl);
            productService.saveProduct(productCreaterequestDto);
            return "redirect:/admin/products";
        } catch (ResponseStatusException e) {
            return "redirect:/admin/products/create-product";
        }
    }

    @GetMapping
    public String getProductsPage(Model model) {
        List<ProductGetResponseDto> products = productService.findAllProducts();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/{productId}")
    public String getProductByProductId(@RequestParam Long productId, Model model) {
        if (productId != null) {
            try {
                ProductGetResponseDto product = productService.findProductByProductId(productId);
                model.addAttribute("products", List.of(product));
            } catch (ResponseStatusException e) {
                model.addAttribute("products", List.of());
                model.addAttribute("error", "해당 상품이 없습니다.");
            }
        }

        return "products";
    }

    @GetMapping("/update/{productId}")
    public String updateProductPage(@PathVariable Long productId, Model model) {
        try {
            ProductGetResponseDto product = productService.findProductByProductId(productId);
            model.addAttribute("product", product);
            return "update-product";
        } catch (ResponseStatusException e) {
            return "redirect:/admin/products";
        }
    }

    @PostMapping("/update/{productId}")
    public String updateProduct(
        @PathVariable Long productId,
        @RequestParam String name,
        @RequestParam Double price,
        @RequestParam String imageUrl
    ) {
        try {
            productService.updateProductByProductId(productId, name, price, imageUrl);
            return "redirect:/admin/products";
        } catch (ResponseStatusException e) {
            return "redirect:/admin/products/update/" + productId;
        }
    }

    @PostMapping("/delete/{productId}")
    public String deleteProduct(@PathVariable Long productId) {
        try {
            productService.deleteProductByProductId(productId);
            return "redirect:/admin/products";
        } catch (ResponseStatusException e) {
            return "redirect:/admin/products";
        }
    }
}