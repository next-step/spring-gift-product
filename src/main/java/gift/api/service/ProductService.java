package gift.api.service;

import gift.api.domain.Product;
import gift.api.dto.ProductRequestDto;
import gift.api.dto.ProductResponseDto;
import gift.api.repository.ProductRepository;
import gift.exception.ProductNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<ProductResponseDto> findAllProducts(Pageable pageable, Long categoryId) {
        Page<Product> page = productRepository.findAllProducts(pageable, categoryId);

        return page.map(ProductResponseDto::from);
    }

    public ProductResponseDto findProductById(Long id) {
        Product product = productRepository.findProductById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getImageUrl()
        );
    }

    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        Long productId = productRepository.createProduct(
                new Product(
                        null,
                        productRequestDto.name(),
                        productRequestDto.price(),
                        productRequestDto.imageUrl()
                )
        );

        return findProductById(productId);
    }

    public ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto) {
        boolean updated = productRepository.updateProduct(
                new Product(
                        id,
                        productRequestDto.name(),
                        productRequestDto.price(),
                        productRequestDto.imageUrl()
                )
        );

        if (!updated) {
            throw new ProductNotFoundException(id);
        }

        return findProductById(id);
    }

    public void deleteProduct(Long id) {
        boolean deleted = productRepository.deleteProduct(id);

        if (!deleted) {
            throw new ProductNotFoundException(id);
        }
    }
}