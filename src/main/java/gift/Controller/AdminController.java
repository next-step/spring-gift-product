package gift.Controller;

import gift.model.Product;
import gift.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    model.addAttribute("mode", "add");
    return "admin/product-form";
  }

  // 상품 수정 page
  @GetMapping("/{id}/edit")
  public String showEditForm(@PathVariable Long id, Model model) {
    Product product = productService.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Product not found"));
    model.addAttribute("product", product);
    model.addAttribute("mode", "edit");
    return "admin/product-form";
  }

  // 상품 추가-add, 수정-mode -> 기능 후 전체 상품 page로 이동
  // 두 페이지 모두 form을 사용해야 한다는 점이 동일해 함께 처리
  @PostMapping
  public String saveProduct(@ModelAttribute Product product, @RequestParam String mode) {
    if ("add".equals(mode)) {
      productService.save(product);
    } else {
      productService.update(product.getId(), product);
    }
    return "redirect:/admin/products";
  }
}
