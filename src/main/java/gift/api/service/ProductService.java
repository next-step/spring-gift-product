package gift.api.service;

import gift.api.domain.Product;
import gift.api.dto.ProductRequestDto;
import gift.api.dto.ProductResponseDto;
import gift.api.repository.ProductRepository;
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
        Product product = productRepository.findProductById(id);

        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getImageUrl()
        );
    }

    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        Product product = productRepository.createProduct(
                new Product(
                        null,
                        productRequestDto.name(),
                        productRequestDto.price(),
                        productRequestDto.imageUrl()
                )
        );

        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getImageUrl()
        );
    }

    public ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto) {
        Product product = productRepository.updateProduct(
                new Product(
                        id,
                        productRequestDto.name(),
                        productRequestDto.price(),
                        productRequestDto.imageUrl()
                )
        );

        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getImageUrl()
        );
    }

    public void deleteProduct(Long id) {
        productRepository.deleteProduct(id);
    }
}
