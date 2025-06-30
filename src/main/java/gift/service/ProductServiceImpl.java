package gift.service;

import gift.dto.PageRequestDto;
import gift.dto.PageResult;
import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        return productRepository.createProduct(productRequestDto);
    }

    @Override
    public PageResult<ProductResponseDto> findAllProducts(PageRequestDto pageRequestDto) {
        return productRepository.findAllProducts(pageRequestDto);
    }

    @Override
    public ProductResponseDto findProductById(Long id) {
        return productRepository.findProductById(id);
    }

    @Override
    public ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto) {
        return productRepository.updateProduct(id, productRequestDto);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteProduct(id);
    }
}
