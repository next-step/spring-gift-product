package gift.controller;

import gift.model.Product;
import gift.repository.ProductDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/products")
public class AdminProductController {
    private final ProductDao productDao;

    public AdminProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("products", productDao.getAllProducts());
        return "product/list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("product",new Product(null,null,null,null));
        return "product/form";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Product product) {
        productDao.insertProduct(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Product product = productDao.getProductById(id);
        model.addAttribute("product", product);
        return "product/form";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Product product) {
        productDao.updateProduct(product.getId(), product, product);
        return "redirect:/admin/products";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        productDao.removeProduct(id);
        return "redirect:/admin/products";
    }
}