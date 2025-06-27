package gift.service;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import gift.repository.ProductRepository;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductResponseDto> findAll() {
        return productRepository.findAll();
    }

    @Override
    public ProductResponseDto createProduct(ProductRequestDto requestDto) {
        return productRepository.createProduct(requestDto);
    }

    @Override
    public ProductResponseDto findProduct(Long productId) {
        return productRepository.findProduct(productId);
    }

    @Override
    public ProductResponseDto updateProduct(Long productId, ProductRequestDto requestDto) {
        return productRepository.updateProduct(productId, requestDto);
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteProduct(productId);
    }

    @Override
    public Map<Long, Product> findAllMap() {
        return Collections.unmodifiableMap(
            productRepository.findAllMap()
        );
    }
}
