package gift.service;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.repository.ProductRepositoryInterface;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements ProductServiceInterface {

    private final ProductRepositoryInterface productRepository;

    public ProductService(ProductRepositoryInterface productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponseDto addProduct(ProductRequestDto requestDto) {
        return productRepository.addProduct(requestDto);
    }

    @Override
    public List<ProductResponseDto> findAllProducts() {
        return productRepository.findAllProducts();
    }

    @Override
    public Optional<ProductResponseDto> findProductById(Long id) {
        return productRepository.findProductById(id);
    }

    @Override
    public Optional<ProductResponseDto> updateProduct(Long id, ProductRequestDto requestDto) {
        return productRepository.updateProduct(id, requestDto);
    }

    @Override
    public boolean deleteProduct(Long id) {
        return productRepository.deleteProduct(id);
    }

}
