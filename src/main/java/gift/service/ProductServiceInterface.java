package gift.service;

import gift.dto.request.ProductRequestDto;
import gift.dto.request.ProductUpdateRequestDto;
import gift.dto.response.ProductResponseDto;
import gift.entity.Product;

interface ProductServiceInterface {

    //상품 조회
    Product getProduct(long productId);

    //상품 추가
    ProductResponseDto createProduct(ProductRequestDto productRequestDto);

    //상품 수정
    ProductResponseDto updateProduct(long productId,
        ProductUpdateRequestDto productUpdateRequestDto);

    //상품 삭제
    void deleteProduct(long productId);

    //상품 유무 확인
    boolean containsProduct(long productId);

}
