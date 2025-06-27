package gift.Controller;

import gift.model.Product;
import gift.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/products")
public class AdminController {

  private final ProductService productService;

  public AdminController(ProductService productService) {
    this.productService = productService;
  }

  // 전체 상품 조회 page
  @GetMapping
  public String adminGetAllProducts(Model model) {
    model.addAttribute("products", productService.findAll());
    return "admin/products";
  }

  // 상품 추가 page
  @GetMapping("/add")
  public String showAddForm(Model model) {
    model.addAttribute("product", new Product());
    model.addAttribute("action", "/admin/products");
    model.addAttribute("method", "post");
    return "admin/product-form";
  }

  // 상품 수정 page
  @GetMapping("/{id}/edit")
  public String showEditForm(@PathVariable Long id, Model model) {
    Product product = productService.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Product not found"));
    model.addAttribute("product", product);
    model.addAttribute("action", "/admin/products/" + id);
    model.addAttribute("method", "put");
    return "admin/product-form";
  }

  // Post로 요청을 받는 경우(상품 추가)
  @PostMapping
  public String addProduct(@ModelAttribute Product product) {
    productService.save(product);
    return "redirect:/admin/products";
  }

  // Put으로 요청을 받는 경우(상품 수정)
  @PutMapping("/{id}")
  public String updateProduct(@PathVariable Long id, @ModelAttribute Product product) {
    productService.update(id, product);
    return "redirect:/admin/products";
  }
}
