package gift.controller;

import gift.dto.ProductRequestDto;
import gift.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/products")
public class ProductViewController {

    private final ProductService productService;

    public ProductViewController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ModelAndView list() {
        Map<String, Object> model = new HashMap<>();
        model.put("products", productService.findAll(0, 100, "id, asc"));
        return new ModelAndView("product/list", model);
    }

    @GetMapping("/new")
    public ModelAndView createForm() {
        Map<String, Object> model = new HashMap<>();
        model.put("product", new ProductRequestDto());
        return new ModelAndView("product/create", model);
    }

    @PostMapping
    public ModelAndView create(@ModelAttribute ProductRequestDto requestDto) {
        productService.create(requestDto);
        return new ModelAndView("redirect:/products");
    }

    @GetMapping("/{id}")
    public ModelAndView detail(@PathVariable Long id) {
        Map<String, Object> model = new HashMap<>();
        model.put("product", productService.find(id));
        return new ModelAndView("product/detail", model);
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editForm(@PathVariable Long id) {
        Map<String, Object> model = new HashMap<>();
        model.put("product", productService.find(id));
        return new ModelAndView("product/edit", model);
    }

    @PostMapping("/{id}")
    public ModelAndView update(@PathVariable Long id, @ModelAttribute ProductRequestDto requestDto) {
        productService.update(id, requestDto);
        return new ModelAndView("redirect:/products");
    }

    @PostMapping("/{id}/delete")
    public ModelAndView delete(@PathVariable Long id) {
        productService.delete(id);
        return new ModelAndView("redirect:/products");
    }
}