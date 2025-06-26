package gift.service;

import gift.dto.ResponseDto;
import gift.dto.request.ProductCreateRequestDto;
import gift.dto.response.ProductCreateResponseDto;
import gift.dto.response.ProductGetResponseDto;
import java.util.List;

public interface ProductService {

    ProductCreateResponseDto saveProduct(ProductCreateRequestDto productCreateRequestDto);

    List<ProductGetResponseDto> findAllProducts();

    ProductGetResponseDto findProductByProductId(Long productId);

    ResponseDto updateProductByProductId(Long productId, String name, Double price,
        String imageUrl);

    ResponseDto deleteProductByProductId(Long productId);
}
