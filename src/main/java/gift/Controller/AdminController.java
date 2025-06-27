package gift.Controller;

import gift.dto.ProductDto;
import gift.model.Product;
import gift.service.ProductService;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
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

  // ✅ 전체 상품 조회
  @GetMapping
  public String adminGetAllProducts(Model model) {
    List<ProductDto> dtoList = productService.findAll().stream()
        .filter(Objects::nonNull) // ← 이중 체크
        .map(ProductDto::from)
        .collect(Collectors.toList());

    model.addAttribute("products", dtoList);
    return "admin/products";
  }


  // ✅ 상품 추가 페이지
  @GetMapping("/add")
  public String showAddForm(Model model) {
    model.addAttribute("product", new ProductDto());
    model.addAttribute("action", "/admin/products");
    model.addAttribute("method", "post");
    return "admin/product-form";
  }

  // ✅ 상품 수정 페이지
  @GetMapping("/{id}/edit")
  public String showEditForm(@PathVariable Long id, Model model) {
    Product product = productService.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Product not found"));

    model.addAttribute("product", ProductDto.from(product));
    model.addAttribute("action", "/admin/products/" + id);
    model.addAttribute("method", "put");
    return "admin/product-form";
  }

  // ✅ 상품 추가
  @PostMapping
  public String addProduct(@ModelAttribute ProductDto productDto) {
    productService.save(productDto.toEntity());
    return "redirect:/admin/products";
  }

  // ✅ 상품 수정
  @PutMapping("/{id}")
  public String updateProduct(@PathVariable Long id, @ModelAttribute ProductDto productDto) {
    productService.update(id, productDto.toEntity());
    return "redirect:/admin/products";
  }
}
