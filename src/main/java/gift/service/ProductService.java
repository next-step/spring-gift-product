package gift.service;

import gift.dto.ProductResponseDto;

public interface ProductService {
    public ProductResponseDto addProduct(String name, Long price, String url);
    public ProductResponseDto findProductById(Long id);
    public ProductResponseDto updateProduct(Long id, String name, Long price, String url);
    public void deleteProduct(Long id);
}
