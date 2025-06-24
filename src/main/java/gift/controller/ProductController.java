package gift.controller;

import gift.dto.ProductRequestDto;
import gift.entity.Product;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ProductController {

    private final Map<Long, Product> products = new HashMap<>();
    private static Long pid = 0L;

    //create
    //생성한 product는 HashMap에 저장
    @PostMapping("/products")
    public Product createProduct(@RequestBody ProductRequestDto requestDto) {
        Product product = new Product(
            ++pid,
            requestDto.getName(),
            requestDto.getPrice(),
            requestDto.getImageUrl()
        );
        products.put(product.getId(), product);
        return product;
    }

    //read
    //특정 상품을 조회(id)
    @GetMapping("/products/{id}")
    public Product findProductById(@PathVariable Long id) {
        Optional<Product> optionalProduct = Optional.ofNullable(products.get(id));
        if (optionalProduct.isPresent()) {
            return optionalProduct.get();
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 상품입니다.");
    }

    //read
    //전체 상품을 조회
    @GetMapping("/products")
    public List<Product> getProducts() {
        return products.values()
            .stream()
            .collect(Collectors.toList());
    }

    //update
    //상품 수정
    @PutMapping("/products/{id}")
    public Product modifyProduct(
        @RequestBody ProductRequestDto requestDto,
        @PathVariable Long id
    ) {
        Optional<Product> optionalProduct = Optional.ofNullable(products.get(id));
        if (optionalProduct.isPresent()) {
            Long found = optionalProduct.get().getId();
            Product product = new Product(
                found,
                requestDto.getName(),
                requestDto.getPrice(),
                requestDto.getImageUrl());
            products.put(found, product);
            return product;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 상품입니다.");
    }

    //delete
    //등록된 상품을 삭제
    @DeleteMapping("/products/{id}")
    public void removeProduct(
        @PathVariable Long id
    ){
        Optional<Product> optionalProduct = Optional.ofNullable(products.get(id));
        if(optionalProduct.isPresent()){
            Long found = optionalProduct.get().getId();
            products.remove(found);
            return;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 상품입니다.");
    }
}
