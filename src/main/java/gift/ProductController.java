package gift;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
 public class ProductController {
    private final ProductRepository products;

    public ProductController(ProductRepository products) {
        this.products = products;
    }

    //상품 조회
    @GetMapping("/api/products")
    public String getProduct(Model model) {
        model.addAttribute("allProducts",products.findAll());
        return "products";
    }
    //상품 단건 조회
    @GetMapping("/api/products/{productId}")
    public String getProductById(@PathVariable long productId, Model model, RedirectAttributes redirectAttributes) {
        Product product = products.get(productId);
        if (product == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "상품이 존재하지 않습니다.");
            return "redirect:/api/products";
        }
        model.addAttribute("product", product);
        return "product"; // product.html로 이동
    }

    //상품 추가
    @PostMapping("/api/products")
    public String createProduct(@RequestParam long productId, @RequestParam String name, @RequestParam int price, @RequestParam String imageURL) {
        Product product = new Product(productId, name, price, imageURL);
        products.add(product);
        return "redirect:/api/products";
    }

    //상품 추가 ModelAttribute 사용
//    @PostMapping("/api/products")
//    public String createProduct(@ModelAttribute Product product) {
//        products.add(product);
//        return "redirect:/api/products";
//    }

    //상품 수정(수정 페이지 이동)
    @PostMapping("/api/products/{productId}")
    public String updateProduct(@PathVariable long productId, @RequestParam String name, @RequestParam int price, @RequestParam String imageURL, RedirectAttributes redirectAttributes) {
        if (products.get(productId) == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "상품이 존재하지 않음");
            return "redirect:/api/products";}
        Product product = new Product(productId, name, price, imageURL);
        products.add(product);
        return "redirect:/api/products";
    }

    //상품 삭제
    @PostMapping("/api/products/delete")
    public String deleteProduct(@RequestParam long productId, RedirectAttributes redirectAttributes) {
        if (products.get(productId) == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "상품이 존재하지 않음");
            return "redirect:/api/products"; }
        products.delete(productId);
        return "redirect:/api/products";
    }
}
