package gift.service;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;

import java.util.List;
import java.util.Optional;

public interface ProductServiceInterface {

    // 상품 생성
    ProductResponseDto addProduct(ProductRequestDto requestDto);

    // 전체 상품 목록 조회
    List<ProductResponseDto> findAllProducts();

    // 선택 상품 조회
    Optional<ProductResponseDto> findProductById(Long id);

    // 상품 수정
    Optional<ProductResponseDto> updateProduct(Long id, ProductRequestDto requestDto);

    // 상품 삭제
    boolean deleteProduct(Long id);
}
