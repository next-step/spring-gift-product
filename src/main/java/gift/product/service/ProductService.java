package gift.product.service;

import gift.exception.EntityNotFoundException;
import gift.product.dto.ProductCreateRequestDto;
import gift.product.dto.ProductResponseDto;
import gift.product.dto.ProductUpdateRequestDto;
import gift.product.entity.Product;
import gift.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    private static final String PRODUCT_NOT_FOUND_MESSAGE = "해당 상품을 찾을 수 없습니다.";
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponseDto createProduct(ProductCreateRequestDto requestDto) {
        Long newProductId = productRepository.save(requestDto);
        return new ProductResponseDto(new Product(newProductId, requestDto));
    }

    @Transactional
    public ProductResponseDto updateProduct(Long id, ProductUpdateRequestDto requestDto) {
        getProduct(id);
        productRepository.update(id, requestDto);
        return getProduct(id);
    }

    @Transactional
    public void deleteProduct(Long id) {
        getProduct(id);
        productRepository.delete(id);
    }

    public List<ProductResponseDto> getProducts() {
        return productRepository.findAll().stream()
                .map(ProductResponseDto::new)
                .toList();
    }

    public ProductResponseDto getProduct(Long id) {
        return new ProductResponseDto(
                productRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException(PRODUCT_NOT_FOUND_MESSAGE)));
    }
}
