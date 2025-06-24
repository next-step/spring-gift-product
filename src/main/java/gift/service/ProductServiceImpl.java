package gift.service;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.repository.ProductRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductResponseDto> findAllProducts() {
        return productRepository.findAllProducts();
    }

    @Override
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        return productRepository.createProduct(productRequestDto);
    }

    @Override
    public ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto)
            throws IllegalArgumentException {
        return productRepository.updateProduct(id, productRequestDto);
    }

    @Override
    public void deleteProduct(Long id) throws IllegalArgumentException {
        productRepository.deleteProduct(id);
    }
}
