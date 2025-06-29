package gift.admin.controller;

import gift.common.annotation.PageParam;
import gift.common.dto.PageRequest;
import gift.common.dto.SortInfo;
import gift.product.dto.CreateProductReqDto;
import gift.product.dto.UpdateProductReqDto;
import gift.product.exception.ProductNotFoundException;
import gift.product.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/products")
public class ProductAdminController {

  private final ProductService productService;

  public ProductAdminController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping
  public String listProducts(@PageParam PageRequest pageRequest, Model model) {
    try {
      var result = productService.getAllByPage(pageRequest);
      model.addAttribute("products", result.content());
      return "product-list";
    } catch (Exception e) {
      model.addAttribute("errorMessage", "상품 목록을 불러오는 중 오류가 발생했습니다.");
      return "error";
    }
  }

  @GetMapping("/add")
  public String showAddForm(Model model) {
    model.addAttribute("product", new CreateProductReqDto(null, null, null, null));
    return "product-add";
  }

  @PostMapping("/add")
  public String addProduct(@Valid @ModelAttribute("product") CreateProductReqDto dto,
      BindingResult bindingResult,
      Model model,
      RedirectAttributes redirectAttributes) {
    if (bindingResult.hasErrors()) {
      return "product-add";
    }

    try {
      productService.createProduct(dto);
      redirectAttributes.addFlashAttribute("successMessage", "상품이 성공적으로 등록되었습니다.");
      return "redirect:/admin/products";
    } catch (Exception e) {
      model.addAttribute("errorMessage", "상품 등록 중 오류가 발생했습니다.");
      return "product-add";
    }
  }

  @GetMapping("/edit/{id}")
  public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
    try {
      var product = productService.getProductById(id);

      model.addAttribute("product", new UpdateProductReqDto(
          product.name(),
          product.price(),
          product.description(),
          product.imageUrl()
      ));
      model.addAttribute("productId", id);
      return "product-edit";
    } catch (ProductNotFoundException e) {
      redirectAttributes.addFlashAttribute("errorMessage", "존재하지 않는 상품입니다.");
      return "redirect:/admin/products";
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("errorMessage", "상품 정보를 불러오는 중 오류가 발생했습니다.");
      return "redirect:/admin/products";
    }
  }

  @PostMapping("/edit/{id}")
  public String updateProduct(@PathVariable Long id,
      @Valid @ModelAttribute("product") UpdateProductReqDto dto,
      BindingResult bindingResult,
      Model model,
      RedirectAttributes redirectAttributes) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("productId", id);
      return "product-edit";
    }

    try {
      productService.updateProduct(id, dto);
      redirectAttributes.addFlashAttribute("successMessage", "상품이 성공적으로 수정되었습니다.");
      return "redirect:/admin/products";
    } catch (ProductNotFoundException e) {
      redirectAttributes.addFlashAttribute("errorMessage", "존재하지 않는 상품입니다.");
      return "redirect:/admin/products";
    } catch (Exception e) {
      model.addAttribute("errorMessage", "상품 수정 중 오류가 발생했습니다.");
      model.addAttribute("productId", id);
      return "product-edit";
    }
  }

  @PostMapping("/delete/{id}")
  public String deleteProduct(@PathVariable Long id, RedirectAttributes redirectAttributes) {
    try {
      productService.deleteProduct(id);
      redirectAttributes.addFlashAttribute("successMessage", "상품이 성공적으로 삭제되었습니다.");
    } catch (ProductNotFoundException e) {
      redirectAttributes.addFlashAttribute("errorMessage", "존재하지 않는 상품입니다.");
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("errorMessage", "상품 삭제 중 오류가 발생했습니다.");
    }
    return "redirect:/admin/products";
  }
}