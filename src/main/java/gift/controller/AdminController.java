package gift.controller;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.service.ProductService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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

  @GetMapping("/add")
  public String addForm(Model model) {
    model.addAttribute("product", new ProductRequestDto("", 0, ""));
    model.addAttribute("mode", "add");
    return "admin/form";
  }

  @PostMapping("/add")
  public String addProduct(@Valid @ModelAttribute("product") ProductRequestDto productRequestDto,
      BindingResult bindingResult,
      Model model) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("mode", "add");
      return "admin/form";
    }
    productService.addProduct(productRequestDto);
    return "redirect:/admin";
  }
}