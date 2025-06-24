package gift.repository;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Repository
public class ProductRepository implements ProductRepositoryInterface {
    private final Map<Long, Product> products = new HashMap<>();
    private long productId = 1L;

    @Transactional
    @Override
    public ProductResponseDto addProduct(ProductRequestDto requestDto) {
        Product product = new Product(
                productId, // 서버에서 id 할당
                requestDto.getName(),
                requestDto.getPrice(),
                requestDto.getImageUrl()
        );
        products.put(productId, product);
        ProductResponseDto responseDto = new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getImageUrl()
        );
        productId++; // id 값 1 증가
        return responseDto;
    }

    @Override
    public List<ProductResponseDto> findAllProducts() {
        List<ProductResponseDto> productList = new ArrayList<>();
        for (Product product : products.values()) {
            productList.add(new ProductResponseDto(
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    product.getImageUrl()
            ));
        }
        return productList;
    }

    @Override
    public Optional<ProductResponseDto> findProductById(Long id) {
        Product product = products.get(id);
        if (product != null) {
            return Optional.of(new ProductResponseDto(
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    product.getImageUrl()
            ));
        } else {
            return Optional.empty();
        }
    }

}
