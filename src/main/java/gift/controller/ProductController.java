package gift.controller;

<<<<<<< step2
import gift.dto.ProductRequestDto;
import gift.entity.Product;
import gift.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;


@Controller
@RequestMapping("/admin/products")
public class ProductController {

    private final ProductService productService;

    //생성자 주입
=======

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import gift.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

>>>>>>> hhseo9519
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

<<<<<<< step2
    @GetMapping
    public String list(Model model) {
        model.addAttribute("products", productService.findAll());
        return "product/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new ProductRequestDto());
        return "product/form";
    }

    @PostMapping
    public String createProduct(@ModelAttribute ProductRequestDto dto) {
        productService.save(dto);
        return "redirect:/admin/products";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Product product = productService.findById(id);
        ProductRequestDto dto = new ProductRequestDto(product.getName(), product.getPrice(),
                product.getImageUrl());
        model.addAttribute("product", dto);
        model.addAttribute("id", id);
        return "product/edit";
    }

    @PostMapping("/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute ProductRequestDto dto) {
        productService.update(id, dto);
        return "redirect:/admin/products";
    }

    @PostMapping("/{id}/delete")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
        return "redirect:/admin/products";
    }


}
=======

    @GetMapping("/{id}")//
    public ResponseEntity<ProductResponseDto> findProduct(@PathVariable Long id){
        return new ResponseEntity<>(productService.findProduct(id),HttpStatus.OK);
    }


    @PostMapping//ResponseEntity는 spring framework에서 제공하는 HTTP 응답 객체
    public ResponseEntity<ProductResponseDto> addProduct(@RequestBody ProductRequestDto productRequestDto){//감싸기
        return new ResponseEntity<>(productService.addProduct(productRequestDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable Long id,@RequestBody ProductRequestDto productRequestDto){
        return new ResponseEntity<>(productService.updateProduct(id,productRequestDto),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new 	ResponseEntity<>(HttpStatus.OK);
    }


}

>>>>>>> hhseo9519
