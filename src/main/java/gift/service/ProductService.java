package gift.service;

import gift.dto.request.ProductCreateRequestDto;
import gift.dto.response.ProductCreateResponseDto;
import gift.dto.response.ProductDeleteResponseDto;
import gift.dto.response.ProductGetResponseDto;
import gift.dto.response.ProductUpdateResponseDto;
import java.util.List;

public interface ProductService {

    ProductCreateResponseDto saveProduct(ProductCreateRequestDto productCreateRequestDto);

    List<ProductGetResponseDto> findAllProducts();

    ProductGetResponseDto findProductByProductId(Long productId);

    ProductUpdateResponseDto updateProductByProductId(Long productId, String name, Double price,
        String imageUrl);

    ProductDeleteResponseDto deleteProductByProductId(Long productId);
}
