//package gift.controller;
//
//import gift.dto.ProductRequestDto;
//import gift.dto.ProductResponseDto;
//import gift.entity.Product;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping("/api/products")
//public class ProductController1 {
//    private final Map<Long, Product> products = new HashMap<>();
//
//    // 생성
//    @PostMapping
//    public ProductResponseDto createProduct(@RequestBody ProductRequestDto requestDto) {
//        Long id = products.isEmpty() ? 1L : Collections.max(products.keySet()) + 1;
//        Product product = new Product(id, requestDto.name(), requestDto.price(), requestDto.imageUrl());
//        products.put(id, product);
//        return new ProductResponseDto(product);
//    }
//
//    // 목록 조회
//    @GetMapping
//    public List<ProductResponseDto> getProducts() {
//        return products.values().stream()
//                .map(ProductResponseDto::new)
//                .collect(Collectors.toList());
//    }
//
//    // 단건 조회
//    @GetMapping("/{id}")
//    public ProductResponseDto getProduct(@PathVariable Long id) {
//        Product product = products.get(id);
//        return new ProductResponseDto(product);
//    }
//
//    // 수정
//    @PutMapping("/{id}")
//    public ProductResponseDto updateProduct(@PathVariable Long id, @RequestBody ProductRequestDto requestDto) {
//        Product product = products.get(id);
//        product.setName(requestDto.name());
//        product.setPrice(requestDto.price());
//        product.setImageUrl(requestDto.imageUrl());
//        return new ProductResponseDto(product);
//    }
//
//    // 삭제
//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteProduct(@PathVariable Long id) {
//        products.remove(id);
//    }
//}
