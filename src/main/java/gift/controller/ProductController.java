package gift.controller;

import gift.dto.ProductRequestDto;
import gift.entity.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

//@ResponseBody + @Controller
@RestController
public class ProductController {

    private final Map<Long, Product> products = new HashMap<>();
    private static Long pid = 0L;

    //create
    //생성한 product는 HashMap에 저장
    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequestDto requestDto) {
        if (checkProduct(requestDto)) {
            Product product = new Product(
                    ++pid,
                    requestDto.getName(),
                    requestDto.getPrice(),
                    requestDto.getImageUrl()
            );
            products.put(product.getId(), product);
            //ResponseEntity의 경우, 객체와 상태를 함께 반환(상태 지정 가능)
            return new ResponseEntity<>(product, HttpStatus.CREATED);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "가격은 음수가 될 수 없으며, 상품명, 가격, 이미지 주소는 필수 값입니다.");
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
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> productList = products.values()
                .stream()
                .collect(Collectors.toList());
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    //update
    //상품 수정
    @PutMapping("/products/{id}")
    public ResponseEntity<Product> modifyProduct(
            @RequestBody ProductRequestDto requestDto,
            @PathVariable Long id
    ) {
        Long found = findProductById(id).getId();
        Product product = new Product(found,
                requestDto.getName(),
                requestDto.getPrice(),
                requestDto.getImageUrl());
        products.put(found, product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    //delete
    //등록된 상품을 삭제
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> removeProduct(@PathVariable Long id) {
        Long productId = findProductById(id).getId();
        products.remove(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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