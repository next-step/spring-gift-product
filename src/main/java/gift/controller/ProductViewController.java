package gift.controller;

import gift.dto.AddProductRequestDto;
import gift.dto.FindProductResponseDto;
import gift.dto.ModifyProductRequestDto;
import gift.service.ProductService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductViewController {
    
    private final ProductService productService;
    
    public ProductViewController(ProductService productService) {
        this.productService = productService;
    }
    
    //main 화면, 상품 목록
    @GetMapping
    public String showListView(Model model) {
        List<FindProductResponseDto> products = productService.findAllProducts();
        model.addAttribute("products", products);
        return "product-list";
    }
    
    //특정 상품 상세 조회
    @GetMapping("{id}")
    public String showProductView(
        @PathVariable Long id,
        Model model
    ) {
        FindProductResponseDto product = productService.findProductWithId(id);
        model.addAttribute("product", product);
        return "product-detail";
    }
    
    //상품 추가 화면, 이름 / 가격 / 이미지 링크 입력 받도록 구성
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("productForm", new AddProductRequestDto());
        return "product-add";
    }
    
    //상품 추가 화면에서 제출 버튼 누르면 동작
    @PostMapping("/add")
    public String addProduct(@ModelAttribute AddProductRequestDto productForm) {
        productService.addProduct(productForm);
        return "redirect:/products";
    }
    
    //상품 목록에서 수정 버튼 누를 시 수정화면 불러옴, 구성은 추가와 유사
    @GetMapping("/edit/{id}")
    public String showModifyForm(@PathVariable Long id, Model model) {
        model.addAttribute("productId", id);
        model.addAttribute("productForm", new ModifyProductRequestDto());
        return "product-edit";
    }
    
    @PutMapping("/edit/{id}")
    public String modifyProduct(@PathVariable Long id,
        @ModelAttribute ModifyProductRequestDto productForm) {
        productService.modifyProductWithId(id, productForm);
        return "redirect:/products";
    }
    
    //삭제 버튼 누를 시 동작
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProductWithId(id);
        return "redirect:/products";
    }
}
