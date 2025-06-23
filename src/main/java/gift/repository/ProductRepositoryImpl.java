package gift.repository;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    // DB connection 하지 않음에 따라 Collection인 HashMap을 사용 (DB 연결 시 없어질 예정)
    private final Map<Long, Product> products = new HashMap<>();
    private long productId = 1L;

    @Override
    public List<ProductResponseDto> findAllProducts() {
        List<ProductResponseDto> productList = new ArrayList<>();

        // 지금까지 담은 물품들이 담긴 HashMap인 products 변수를 for loop를 돌면서 productList에 add 한다.
        // 파이썬 습관 때문에 products를 그냥 넣으면 loop를 돌 수 있을 것이라고 생각했는데, 아예 다르게 Map.Entry<>로 나오는 것을 알았음.
        for (Map.Entry<Long, Product> entry : products.entrySet()) {
            productList.add(
                new ProductResponseDto(entry.getValue())); // Product -> ProductResponseDto
        }
        return productList;
    }

    @Override
    public ProductResponseDto createProduct(ProductRequestDto requestDto) {
        products.put(productId,
            new Product(requestDto.getId(), requestDto.getName(), requestDto.getPrice(),
                requestDto.getImageUrl()));
        productId++; // id 값 1 증가

        return new ProductResponseDto(requestDto.getId(), requestDto.getName(),
            requestDto.getPrice(),
            requestDto.getImageUrl());
    }

    @Override
    public ProductResponseDto findProduct(Long productId) {
        Product product = products.get(productId);
        ProductResponseDto responseDto = new ProductResponseDto(product);
        System.out.println(responseDto.getClass());
        return new ProductResponseDto(product);
    }
}
