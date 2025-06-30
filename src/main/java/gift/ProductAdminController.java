package gift;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/api/admin")
public class ProductAdminController {
    private final ProductDao productdao;

    public ProductAdminController(ProductDao productdao) {
        this.productdao = productdao;
    }

    @GetMapping("/product/list")
    public String findAll(Model model) {
        List<Product> products = productdao.getAll();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/product/add")
    public String addForm(Model model) {
        model.addAttribute("productdto", new ProductDto());
        return "addForm";
    }

    @PostMapping("/product/add")
    public String saveProduct(@ModelAttribute ProductDto productdto) {
        ProductDto productdto1;
        productdto1 = new ProductDto(productdto.getName(), productdto.getPrice(), productdto.getImageUrl());
        productdao.save(productdto1);
        return "redirect:/api/admin/product/list";
    }

    @ResponseBody
    @GetMapping("/product/{id}")
    public Product findById(@PathVariable String id) {
        return productdao.findById(id);
    }

    @GetMapping("/product/{id}/update")
    public String updateForm(@PathVariable String id, Model model) {
        Product product = productdao.findById(id);
        model.addAttribute("product", product);
        return "updateForm";
    }

    @PatchMapping("/product/{id}/update")
    public String updateProduct(@PathVariable String id, @ModelAttribute ProductDto updateProductdto) {
        ProductDto productdto1;
        productdto1 = new ProductDto(updateProductdto.getName(), updateProductdto.getPrice(), updateProductdto.getImageUrl());
        Product product = productdao.findById(id);
        productdao.update(id, productdto1);
        return "redirect:/api/admin/product/list";
    }

    @DeleteMapping("/product/{id}/delete")
    public String deleteById(@PathVariable String id) {
        Product product = productdao.findById(id);
        productdao.delete(id);
        return "redirect:/api/admin/product/list";
    }
}
