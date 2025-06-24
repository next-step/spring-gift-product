package gift.service;

import gift.dto.ProductResponseDto;

import java.util.List;

public interface ProductService {

    List<ProductResponseDto> findAllProducts();

}
