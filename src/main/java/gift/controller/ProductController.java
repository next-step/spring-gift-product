package gift.controller;

import gift.dto.ProductRequestDto;
import gift.entity.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Key;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class ProductController {
    private final Map<Long, Product> products = new HashMap<>();
    private static Long id = 0L;

    //create
    //생성한 product는 HashMap에 저장
    @PostMapping("/products")
    public Product createProduct(@ModelAttribute ProductRequestDto requestDto){
        Product product = new Product(
                ++id,
                requestDto.getName(),
                requestDto.getPrice(),
                requestDto.getImageUrl()
        );
        products.put(product.getId(), product);
        return product;
    }

    //read
    //특정 상품을 조회(id)

    //read
    //전체 상품을 조회
    @GetMapping("/products")
    public List<Product> getProducts(){
        return products.values()
                .stream()
                .collect(Collectors.toList());
    }

    //update
    //상품의 일부 내용만을 수정

    //delete
    //등록된 상품을 삭제
}
