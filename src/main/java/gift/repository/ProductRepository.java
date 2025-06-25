package gift.repository;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductRepository {

    // id 지정하는 변수
    private static Long label = 1L;

    // DB 대체 제품 저장 컬렉션
    private final Map<Long, Product> products = new HashMap<>();

    public Optional<Product> findProductById(long id) {
        Product product = products.get(id);
        return Optional.ofNullable(product);
    }

    public List<ProductResponseDto> findAllProducts() {
        List<ProductResponseDto> list = new ArrayList<>();
        for(Product product : products.values()){
            list.add(new ProductResponseDto(product));
        }
        return list;
    }

    public ProductResponseDto saveProduct(ProductRequestDto requestDto) {
        Product product = new Product(label, requestDto.name(), requestDto.price(), requestDto.imageUrl());
        products.put(label, product);
        label++;
        return new ProductResponseDto(product);
    }

    public boolean updateProduct(Long id, ProductRequestDto requestDto) {
        Product product = new Product(id, requestDto.name(), requestDto.price(), requestDto.imageUrl());
        return products.replace(id, product) != null;
    }

    public boolean deleteProduct(Long id) {
        return products.remove(id) != null;
    }
}
