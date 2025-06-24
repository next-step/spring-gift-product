package gift.service;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import gift.repository.ProductRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponseDto createProduct(ProductRequestDto requestDto) {
        return productRepository.createProduct(requestDto);
    }

    @Override
    public List<ProductResponseDto> findAllProducts() {
        return productRepository.findAllProducts();
    }

    @Override
    public ProductResponseDto findProductById(Long id) {
        return productRepository.findProductById(id);
    }
}
