package gift.Controller;
/*
import gift.Entity.Product;
import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final Map<Long, Product> products = new HashMap<>();
    // 자동으로 id를 증가시켜 줌
    private final AtomicLong idGenerator = new AtomicLong(1);

    public ProductController() {
        // 자동으로 id 증가
        Long id = idGenerator.getAndIncrement();
        products.put(id, new Product(id, "아이스 카페 아메리카노 T", 4500,
                "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"));
    }

    @GetMapping
    public List<ProductResponseDto> getProducts() {
        List<ProductResponseDto> result = new ArrayList<>();
        for (Product p : products.values()) {
            result.add(new ProductResponseDto(p));
        }
        return result;
    }

    @PostMapping
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto requestDto) {
        // 자동으로 id 증가
        Long id = idGenerator.getAndIncrement();

        Product newProduct = new Product(id, requestDto.getName(), requestDto.getPrice(), requestDto.getImageUrl());
        products.put(id, newProduct);
        return new ProductResponseDto(newProduct);
    }

    @PutMapping("/{id}")
    public ProductResponseDto updateProduct(@PathVariable Long id, @RequestBody ProductRequestDto requestDto) {

        Product product = products.get(id);

        product.update(requestDto);
        // update된 객체를 다시 put 해주어 update를 완료합니다.
        products.put(id, product);

        return new ProductResponseDto(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (products.remove(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
*/