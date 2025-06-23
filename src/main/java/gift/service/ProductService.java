package gift.service;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import java.util.List;
import org.springframework.http.HttpStatusCode;

public interface ProductService {

    List<ProductResponseDto> findAllProducts();

    ProductResponseDto createProduct(ProductRequestDto requestDto);

    ProductResponseDto findProduct(Long productId);
}
