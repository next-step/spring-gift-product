package gift;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.*;

@Controller
@RequestMapping("/api")
public class ProductController {
    private final ProductDao productDao;

    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @GetMapping("/products")
    public String listProducts(Model model) {
        List<Product> products = new ArrayList<>();
        try {
            products = productDao.getAll();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/product/add")
    public String addForm(Model model) {
        model.addAttribute("productdto", new ProductDTO());
        return "addForm";
    }

    @PostMapping("/product/add")
    public String createProduct(@ModelAttribute ProductDTO productdto) {
        ProductDTO productdto1;
        productdto1 = new ProductDTO(productdto.getName(), productdto.getPrice(), productdto.getImageUrl());
        try {
            productDao.save(productdto1);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return "redirect:/api/products";
    }

    @ResponseBody
    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable Long id) {
        Product product = new Product();
        try {
            product = productDao.findById(id);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @GetMapping("/product/{id}/update")
    public String updateForm(@PathVariable Long id, Model model) {
        Product product = new Product();
        try {
            product = productDao.findById(id);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        model.addAttribute("product", product);
        return "updateForm";
    }

    @PatchMapping("/product/{id}/update")
    public String updateProduct(@PathVariable Long id, @ModelAttribute ProductDTO updateProductdto) {
        ProductDTO productdto1;
        productdto1 = new ProductDTO(updateProductdto.getName(), updateProductdto.getPrice(), updateProductdto.getImageUrl());
        Product product = new Product();
        try {
            product = productDao.findById(id);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        if( product != null) {
            try {
                productDao.update(id, productdto1);
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/api/products";
    }

    @DeleteMapping("/product/{id}/delete")
    public String deleteProduct(@PathVariable Long id) {
        Product product = new Product();
        try {
            product = productDao.findById(id);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        if( product != null) {
            try {
                productDao.delete(id);
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/api/products";
    }

    @ResponseBody
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
