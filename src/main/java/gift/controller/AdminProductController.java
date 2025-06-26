package gift.controller;

import gift.dto.request.ProductRequestDto;
import gift.entity.Product;
import gift.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/admin/products")
public class AdminProductController {
    private final ProductService productService;

    public AdminProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping
    public String showProductList(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "admin/product-list";
    }


    /// /////////////등록///////////////

    @GetMapping("/new")
    public String  showCreateProductForm(Model model) {
        model.addAttribute("product", new ProductRequestDto());
        return "admin/product-form";
    }

    @PostMapping
    public String addProduct(@ModelAttribute ProductRequestDto productRequestDto) {
        productService.createProduct(productRequestDto);
        return "redirect:/admin/products";
    }


    /// /// 수정///
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        ProductRequestDto dto = new ProductRequestDto(product.getName(), product.getPrice(), product.getImageUrl());
        model.addAttribute("product", dto);
        model.addAttribute("productId", id);
        return "admin/product-edit-form";
    }

    @PostMapping("/{id}")
    public String updateProduct(@PathVariable Long id,@ModelAttribute ProductRequestDto productRequestDto) {
        productService.updateProduct(id, productRequestDto);
        return "redirect:/admin/products";

    }


    /////////삭제/////////////
    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/admin/products";
    }

}
