package gift.controller;

import gift.dto.ProductRequestDto;
import gift.entity.Product;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

@RequestMapping("/admin") //prefix설정
@Controller//Controller는 mvc에서 화면을 구성하기 위해서 뷰 이름을 반환하고 ViewResolver를 거치게 됩니다.
public class AdminController {
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
            return "redirect:/admin/products"; //GetMapping 되어 있는 것을 호출,,,?
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
    @GetMapping("/products/info")
    public String getProduct(
            @RequestParam Long id,
            Model model
    ) {
        Product product = findProductById(id);
        model.addAttribute("product", product);
        return "productinfo";
    }

    //read
    //전체 상품을 조회
    @GetMapping("/products")
    public String getProducts(Model model) {
        List<Product> productList = products.values()
                .stream()
                .collect(Collectors.toList());
        model.addAttribute("productList",productList);
        return "home";
        //return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    //modify.html을 불러오기 위한 메서드
    @GetMapping("/products/modify")
    public String modifyForm(
            @RequestParam Long id,
            Model model
    ){
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
        Product product = new Product(id,
                requestDto.getName(),
                requestDto.getPrice(),
                requestDto.getImageUrl());
        products.put(id, product);
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

