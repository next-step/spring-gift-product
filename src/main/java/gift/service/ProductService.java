package gift.service;

import gift.dto.ResponseDto;
import gift.dto.request.ProductCreateRequestDto;
import gift.dto.response.ProductCreateResponseDto;
import java.util.List;

public interface ProductService {

    ProductCreateResponseDto saveProduct(ProductCreateRequestDto productCreateRequestDto);

    List<ResponseDto> findAllProducts();

    ResponseDto findProductByProductId(Long productId);

    ResponseDto updateProductByProductId(Long productId, String name, Double price,
        String imageUrl);

    ResponseDto deleteProductByProductId(Long productId);
}
