package gift.repository;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryInterface {

    // 상품 생성을 위한 ID 얻기
    long getNewProductId();

    // 상품 생성
    Product addProduct(Product product);

    // 전체 상품 목록 조회
    List<Product> findAllProducts();

    // 선택 상품 조회
    Optional<Product> findProductById(Long id);

    // 상품 수정
    Optional<Product> updateProduct(Long id, Product product);

    // 상품 삭제 (성공시 true, 없으면 false)
    boolean deleteProduct(Long id);
}
