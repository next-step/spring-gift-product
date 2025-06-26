package gift.controller;

import gift.dto.CreateProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.service.ProductService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/boards")
public class AdminController {

    private final ProductService productService;

    public AdminController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String showAdminPage(Model model) {
        List<ProductResponseDto> products = productService.findAllProducts();
        model.addAttribute("products", products);
        return "dashboard";
    }

    @GetMapping("/add")
    public String showCreatePage(Model model) {
        CreateProductRequestDto requestDto = new CreateProductRequestDto();
        model.addAttribute("requestDto", requestDto);
        return "createForm";
    }

    @PostMapping
    public String createProduct(@ModelAttribute CreateProductRequestDto requestDto) {
        System.out.println(requestDto.getName());
        productService.createProduct(requestDto);
        return "redirect:/admin/boards";
    }

}
