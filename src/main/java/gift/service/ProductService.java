package gift.service;

import gift.dto.RequestDto;
import gift.dto.ResponseDto;
import java.util.List;

public interface ProductService {

    ResponseDto saveProduct(RequestDto requestDto);

    List<ResponseDto> findAllProducts();

    ResponseDto findProductByProductId(Long productId);

    ResponseDto updateProductByProductId(Long productId, String name, Double price,
        String imageUrl);
}
