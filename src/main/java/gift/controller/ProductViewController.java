package gift.controller;

import gift.dto.api.AddProductRequestDto;
import gift.dto.api.FindProductResponseDto;
import gift.dto.api.ModifyProductRequestDto;
import gift.dto.htmlform.AddProductForm;
import gift.dto.htmlform.ModifyProductForm;
import gift.service.ProductService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductViewController {
    
    private final ProductService productService;
    private final String PRODUCTS_LIST_PATH = "/products";
    
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
        ModifyProductForm modifyForm = new ModifyProductForm(product.getName(),
            product.getPrice(), product.getImageUrl());
        
        model.addAttribute("product", product);
        model.addAttribute("modifyForm", modifyForm);
        
        return "product-detail";
    }
    
    //상품 추가 화면, 이름 / 가격 / 이미지 링크 입력 받도록 구성
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("productForm", new AddProductForm());
        
        return "product-add";
    }
    
    //상품 추가 화면에서 제출 버튼 누르면 동작
    @PostMapping("/add")
    public String addProduct(@ModelAttribute AddProductForm productForm) {
        AddProductRequestDto requestDto = new AddProductRequestDto(
            productForm.getName(),
            productForm.getPrice(),
            productForm.getImageUrl()
        );
        
        productService.addProduct(requestDto);
        
        return "redirect:" + PRODUCTS_LIST_PATH;
    }
    
    //상품 목록에서 수정 버튼 누를 시 수정화면 불러옴, 구성은 추가와 유사
    @GetMapping("/edit/{id}")
    public String showModifyForm(@PathVariable Long id, Model model) {
        model.addAttribute("productId", id);
        model.addAttribute("productForm", new ModifyProductForm());
        
        return "product-edit";
    }
    
    //수정 화면에서 수정 누를 시 동작
    @PutMapping("/edit/{id}")
    public String modifyProduct(@PathVariable Long id,
        @ModelAttribute ModifyProductForm productForm) {
        ModifyProductRequestDto requestDto = new ModifyProductRequestDto(
            productForm.getName(),
            productForm.getPrice(),
            productForm.getImageUrl()
        );
        
        productService.modifyProductWithId(id, requestDto);
        
        return "redirect:" + PRODUCTS_LIST_PATH;
    }
    
    //조회 화면에서 수정 누를 시 동작
    @PatchMapping("/edit/{id}")
    public String modifyInfoProduct(@PathVariable Long id,
        @ModelAttribute ModifyProductForm modifyForm) {
        ModifyProductRequestDto requestDto = new ModifyProductRequestDto(
            modifyForm.getName(),
            modifyForm.getPrice(),
            modifyForm.getImageUrl()
        );
        
        productService.modifyProductInfoWithId(id, requestDto);
        
        return "redirect:" + PRODUCTS_LIST_PATH;
    }
    
    //삭제 버튼 누를 시 동작
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProductWithId(id);
        
        return "redirect:" + PRODUCTS_LIST_PATH;
    }
}
