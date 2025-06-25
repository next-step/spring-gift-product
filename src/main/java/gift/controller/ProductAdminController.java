package gift.controller;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.service.ProductService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/products")
public class ProductAdminController {

  private final ProductService productService;

  public ProductAdminController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping
  public String productList(Model model) {
    List<ProductResponseDto> products = productService.searchAllProducts();
    model.addAttribute("products", products);
    return "admin/product-list";
  }

  @GetMapping("/new")
  public String showCreateForm() {
    return "admin/product-form";
  }

  @PostMapping
  public String create(@ModelAttribute ProductRequestDto productDto) {
    productService.createProduct(productDto);
    return "redirect:/admin/products";
  }

  @GetMapping("/{id}/edit")
  public String showEditForm(@PathVariable Long id, Model model) {
    model.addAttribute("id", id);
    return "admin/product-update";
  }

  @PostMapping("/{id}")
  public String update(@PathVariable Long id, @ModelAttribute ProductRequestDto productDto) {
    productService.updateProduct(id, productDto);
    return "redirect:/admin/products";
  }

  @PostMapping("/{id}/delete")
  public String delete(@PathVariable Long id) {
    productService.deleteProduct(id);
    return "redirect:/admin/products";
  }
}
