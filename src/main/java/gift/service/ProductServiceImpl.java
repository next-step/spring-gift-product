package gift.service;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import gift.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    // 임시 DB
    private final Map<Long, Product> productList = new HashMap<>();

    // 상품 생성
    @Override
    public ProductResponseDto addProduct(ProductRequestDto requestDto) {

        // 상품 Id 계산
        Long ProductId = productList.isEmpty() ? 1 : Collections.max(productList.keySet()) + 1;

        // 상품 객체 생성
        Product product = new Product(ProductId, requestDto.getName(), requestDto.getPrice(), requestDto.getImageUrl());

        // 임시 DB에 저장
        productList.put(ProductId, product);

        return new ProductResponseDto(product);
    }

    @Override
    public ProductResponseDto getProductById(Long id) {

        Product product = productList.get(id);

        if (product == null) {
            throw new ProductNotFoundException(id); // 예외 처리
        }

        return new ProductResponseDto(product);
    }
}
