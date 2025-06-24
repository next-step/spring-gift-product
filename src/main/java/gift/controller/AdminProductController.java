package gift.controller;


import gift.dto.CreateProductRequestDto;
import gift.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/admin/products")
public class AdminProductController {
    private final ProductService productService;
    public AdminProductController(ProductService productService)
    {
        this.productService = productService;
    }

    //list 목록 보기
    @GetMapping
    public String list(Model model)
    {
        model.addAttribute("products",productService.getAll());
        return "admin/products/list";
    }
    //register
    @GetMapping("/new")
    public String showCreateForm(Model model)
    {
        model.addAttribute("product", new CreateProductRequestDto());
        return "admin/products/new";
    }

    // post register
    @PostMapping
    public String create(@Valid @ModelAttribute("product") CreateProductRequestDto dto, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return "admin/products/new";
        }
        productService.create(dto);
        return "redirect:/api/admin/products";
    }

}
