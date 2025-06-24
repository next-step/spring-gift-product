package gift.service;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;

public interface ProductService {

    ProductResponseDto addProduct(ProductRequestDto requestDto);
}
