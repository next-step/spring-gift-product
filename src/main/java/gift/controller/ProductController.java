package gift.controller;

import gift.dto.ProductRequestDto;
import gift.entity.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

//@ResponseBody + @Controller
//@RestController
@Controller
public class ProductController {

    private final Map<Long, Product> products = new HashMap<>();
    private static Long pid = 0L;

    //create
    //생성한 product는 HashMap에 저장
    @PostMapping("/products")
    public String createProduct(@ModelAttribute ProductRequestDto requestDto) {
        if (checkProduct(requestDto)) {
            Product product = new Product(
                ++pid,
                requestDto.getName(),
                requestDto.getPrice(),
                requestDto.getImageUrl()
            );
            products.put(product.getId(), product);
            return "redirect:/products"; //GetMapping 되어 있는 것을 호출,,,?
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
            "가격은 음수가 될 수 없으며, 상품명, 가격, 이미지 주소는 필수 값입니다.");
    }

    //form.html을 불러오기 위한 메서드
    @GetMapping("/products/new")
    public String productForm(){
        return "form";
    }

    //read
    //특정 상품을 조회(id)
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        Product product = findProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    //read
    //전체 상품을 조회
    @GetMapping("/products")
    public String getProducts(Model model) {
        List<Product> productList = products.values()
            .stream()
            .collect(Collectors.toList());
        model.addAttribute("productList",productList);
        return "main";
        //return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    //modify.html을 불러오기 위한 메서드
    @GetMapping("/products/modify/{id}")
    public String modifyForm(
        @PathVariable Long id,
        Model model
    ){
        //String test = "test" + id.toString();
        Product product = products.get(id);
        model.addAttribute("product",product);
        return "modify";
    }

    //update
    //상품 수정
    @PutMapping("/products")
    public String modifyProduct(
        @ModelAttribute ProductRequestDto requestDto,
        @RequestParam Long id
    ) {
        Long found = findProductById(id).getId();
        Product product = new Product(found,
            requestDto.getName(),
            requestDto.getPrice(),
            requestDto.getImageUrl());
        products.put(found, product);
        return "redirect:/products";
    }

    //delete
    //등록된 상품을 삭제
    @GetMapping("/products/remove/{id}")
    public String removeProduct(@PathVariable Long id) {
        Long found = findProductById(id).getId();
        products.remove(found);
        return "redirect:/products";
    }

    public boolean checkProduct(ProductRequestDto requestDto) {
        //상품명, 가격, 이미지의 경우 모두 필수 값
        if (requestDto.getName() == null || requestDto.getPrice() == null
            || requestDto.getImageUrl() == null) {
            return false;
        }
        //가격은 0이하가 될 수 없음
        else if (requestDto.getPrice() < 0) {
            return false;
        }
        return true;
    }

    public Product findProductById(Long id){
        Optional<Product> optionalProduct = Optional.ofNullable(products.get(id));
        if (optionalProduct.isPresent()) {
            return optionalProduct.get();
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 상품입니다.");
    }
}
