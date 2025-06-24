package gift.service;

import gift.controller.ProductController;
import gift.dto.CreateProductRequestDto;
import gift.dto.ProductResponseDto;

public interface ProductService {
    ProductResponseDto createProduct(CreateProductRequestDto requestDto);
}
