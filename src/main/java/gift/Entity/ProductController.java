package gift.Entity;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final Map<Long, Product> products = new HashMap<>();

    public ProductController() {
        Long id = products.isEmpty() ? 1 : Collections.max(products.keySet()) + 1;
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
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto req) {
        // 식별자가 1씩 증가 하도록 만듦
        Long id = products.isEmpty() ? 1 : Collections.max(products.keySet()) + 1;

        Product newProduct = new Product(id, req.getName(), req.getPrice(), req.getImageUrl());
        products.put(id, newProduct);
        return new ProductResponseDto(newProduct);
    }

    @PutMapping("/{id}")
    public ProductResponseDto updateProduct(@PathVariable Long id, @RequestBody ProductRequestDto requestDto) {

        Product product = products.get(id);

        product.update(requestDto);

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
