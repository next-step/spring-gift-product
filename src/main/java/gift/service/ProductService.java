package gift.service;

import gift.dto.request.ProductCreateRequestDto;
import gift.dto.response.ProductCreateResponseDto;
import gift.dto.response.ProductGetResponseDto;
import java.util.List;

public interface ProductService {

    ProductCreateResponseDto saveProduct(ProductCreateRequestDto productCreateRequestDto);

    List<ProductGetResponseDto> findAllProducts();

    ProductGetResponseDto findProductById(Long productId);

    void updateProductById(Long productId, String name, Double price,
        String imageUrl);

    void deleteProductById(Long productId);
}
