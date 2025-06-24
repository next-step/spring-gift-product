package gift.controller;


import gift.dto.CreateProductRequestDto;
import gift.dto.UpdateProductRequestDto;
import gift.entity.Product;
import gift.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    // Update
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model)
    {
        Product product=productService.getById(id)
                .orElseThrow(()->new RuntimeException("Product not found"));
        UpdateProductRequestDto dto=new UpdateProductRequestDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setImageUrl(product.getImageUrl());

        model.addAttribute("product",dto);
        model.addAttribute("productId",id);
        return "admin/products/edit";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id, @Valid @ModelAttribute UpdateProductRequestDto dto, BindingResult bindingResult, Model model)
    {
        if (bindingResult.hasErrors())
        {
            model.addAttribute("productId",id);
            model.addAttribute("product",dto);
            return "admin/products/edit";
        }
        productService.update(id, dto);
        return "redirect:/api/admin/products";
    }

    // Delete
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id)
    {
        productService.delete(id);
        return "redirect:/api/admin/products";
    }
}
