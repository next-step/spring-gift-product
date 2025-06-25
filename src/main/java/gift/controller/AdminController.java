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
public class AdminController {

  ProductService service;

  public AdminController(ProductService service) {
    this.service = service;
  }

  @GetMapping("")
  public String ShowAllProduct(Model model) {
    List<ProductResponseDto> responseDtoList = service.findAllProduct();
    model.addAttribute("responseDtoList", responseDtoList);
    return "list";
  }

  @GetMapping("/new")
  public String createForm(Model model) {
    model.addAttribute("requestDto", new ProductRequestDto());
    return "createProductForm";
  }

  @PostMapping("/new")
  public String create(@ModelAttribute ProductRequestDto requestDto) {
    service.createProduct(requestDto);
    return "redirect:/admin/products";
  }

  @GetMapping("/{id}/update")
  public String updateForm(@PathVariable("id") Long id, Model model) {
    ProductResponseDto productById = service.findProductById(id);
    ProductRequestDto productRequestDto = new ProductRequestDto(productById);
    model.addAttribute("requestDto", productRequestDto);
    return "updateForm";
  }

  @PostMapping(value = "/{id}/update")
  public String update(@PathVariable("id") Long id, ProductRequestDto requestDto) {
    service.updateProduct(id, requestDto);
    return "redirect:/admin/products";
  }
}
