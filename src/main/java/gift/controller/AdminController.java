package gift.controller;

import gift.dto.ProductRequest;
import gift.entity.Product;
import gift.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/products")
public class AdminController {

    private final ProductService productService;

    public AdminController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("productRequest", new ProductRequest(null, "", null, ""));
        model.addAttribute("action", "/admin/products/new");
        return "admin/product-form";
    }

    @PostMapping("/new")
    public String createProduct(
        @ModelAttribute("productRequest")
        ProductRequest productRequest,
        BindingResult result,
        RedirectAttributes redirectAttributes,
        Model model
    ) {
        if (result.hasErrors()) {
            model.addAttribute("action", "/admin/products/new");
            return "admin/product-form";
        }
        try {
            productService.createProduct(productRequest);
            redirectAttributes.addFlashAttribute("message", "상품이 성공적으로 등록되었습니다.");
            return "redirect:/admin/products";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("action", "/admin/products/new");
            return "admin/product-form";
        }
    }

    @GetMapping("/{id}")
    public String showProduct(
        @PathVariable Long id, Model model
    ) {
        try {
            Product product = productService.getProduct(id);
            model.addAttribute("product", product);
            return "admin/product-detail";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "admin/products";
        }
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(
        @PathVariable Long id, Model model
    ) {
        try {
            Product product = productService.getProduct(id);
            ProductRequest productRequest = new ProductRequest(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getImageUrl()
            );
            model.addAttribute("productRequest", productRequest);
            model.addAttribute("action", "/admin/products/" + id + "/edit");
            return "admin/product-form";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "admin/products";
        }
    }

    @PostMapping("/{id}/edit")
    public String updateProduct(
        @PathVariable Long id,
        @ModelAttribute("productRequest")
        ProductRequest productRequest,
        RedirectAttributes redirectAttributes,
        Model model
    ) {
        try {
            productService.updateProduct(id, productRequest);
            redirectAttributes.addFlashAttribute("message", "상품이 성공적으로 수정되었습니다.");
            return "redirect:/admin/products";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("action", "/admin/products/" + id + "/edit");
            return "admin/product-form";
        }
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(
        @PathVariable
        Long id,
        RedirectAttributes redirectAttributes,
        Model model
    ) {
        try {
            productService.deleteProduct(id);
            redirectAttributes.addFlashAttribute("message", "상품이 성공적으로 삭제되었습니다.");
            return "redirect:/admin/products";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "admin/product-list";
        }
    }

    @GetMapping
    public String listProducts(Model model) {
        try {
            model.addAttribute("products", productService.getAllProducts());
            return "admin/product-list";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "admin/product-list";
        }
    }

}