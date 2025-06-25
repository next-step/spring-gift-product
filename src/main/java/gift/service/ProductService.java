package gift.service;

import gift.domain.Product;
import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.repository.ProductRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductResponseDto> findAllProducts() {
        return productRepository.findAllProducts()
                .stream()
                .map(product -> new ProductResponseDto(
                        product.getId(),
                        product.getName(),
                        product.getPrice(),
                        product.getImageUrl()))
                .toList();
    }

    public ProductResponseDto findProductById(Long id) throws IllegalArgumentException {
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

    public ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto)
            throws IllegalArgumentException {
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

    public void deleteProduct(Long id) throws IllegalArgumentException {
        productRepository.deleteProduct(id);
    }
}
