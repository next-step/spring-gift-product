package gift.controller;

import gift.dto.RequestDto;
import gift.entity.Product;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/admin/products")
public class AdminProductController {

    private final Map<Long, Product> products = new HashMap<>();

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
        // ID 생성 로직을 ProductController와 동일하게 적용
        long productId = products.isEmpty() ? 1 : Collections.max(products.keySet()) + 1;

        // 입력값 검증
        if (name == null || price == null || imageUrl == null) {
            return "redirect:/admin/products/create-product";
        }

        Product product = new Product(
            productId,
            name,
            price,
            imageUrl
        );

        products.put(productId, product);
        return "redirect:/admin/products/create-product";
    }

    @GetMapping
    public String getProductsPage(Model model) {
        // products가 null이 아닌지 확인
        if (products != null) {
            model.addAttribute("products", products.values());
        }
        return "products";
    }

    @GetMapping("/update/{productId}")
    public String updateProductPage(@PathVariable Long productId, Model model) {
        Product product = products.get(productId);

        if (product == null) {
            return "redirect:/admin/products";
        }

        model.addAttribute("product", product);
        return "update-product";
    }

    @PostMapping("/update/{productId}")
    public String updateProduct(
        @PathVariable Long productId,
        @RequestParam String name,
        @RequestParam Double price,
        @RequestParam String imageUrl
    ) {
        Product product = products.get(productId);

        if (product == null) {
            return "redirect:/admin/products";
        }

        if (name == null || price == null || imageUrl == null) {
            return "redirect:/admin/products/update/" + productId;
        }

        product.update(new RequestDto(name, price, imageUrl));
        return "redirect:/admin/products";
    }

    @PostMapping("/delete/{productId}")
    public String deleteProduct(@PathVariable Long productId) {
        Product product = products.get(productId);

        if (product == null) {
            return "redirect:/admin/products";
        }

        products.remove(productId);
        return "redirect:/admin/products";
    }

}