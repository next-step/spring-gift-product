package gift.controller;

import gift.dto.ProductResponseDto;
import gift.service.ProductService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

  private final ProductService productService;

  public AdminController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping
  public String productList(Model model) {
    List<ProductResponseDto> products = productService.findAllProduct();
    model.addAttribute("products", products);
    return "admin/list"; // templates/admin/list.html
  }
}