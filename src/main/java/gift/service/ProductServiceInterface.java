package gift.service;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;

import java.util.List;
import java.util.Optional;

public interface ProductServiceInterface {

    // 상품 생성
    ProductResponseDto addProduct(ProductRequestDto requestDto);

}
