package gift.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;

import java.util.List;

public interface ProductRepository {
    ProductResponseDto createProduct(ProductRequestDto productRequestDto);

    Page<ProductResponseDto> findAllProducts(Pageable pageable);

    ProductResponseDto findProductById(Long id);

    ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto);

    void deleteProduct(Long id);
}
