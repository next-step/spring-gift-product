package gift.controller;

import gift.dto.ProductRequestDto;
import gift.entity.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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
        products.put(1L, product);
        return product;
    }

    //read

    //update
    //상품의 일부 내용만을 수정

    //delete
    //등록된 상품을 삭제
}
