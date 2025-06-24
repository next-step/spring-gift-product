package gift.service;

import gift.dto.ProductAddResponseDto;

public interface ProductService {
    public ProductAddResponseDto addProduct(String name, Long price, String url);
}
