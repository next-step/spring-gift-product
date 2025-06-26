package gift.service;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import gift.repository.ProductRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponseDto saveProduct(ProductRequestDto productRequestDto) {
        Product product = new Product(null, productRequestDto.getName(),
                productRequestDto.getPrice(), productRequestDto.getImageUrl());

        Product savedProduct = productRepository.saveProduct(product);

        return new ProductResponseDto(savedProduct);
    }

    public ProductResponseDto findProduct(long productId) {
        Product product = productRepository.findProduct(productId);
        if (product == null) {
            throw new IllegalArgumentException();
        }

        return new ProductResponseDto(product);
    }

    public ProductResponseDto updateProduct(long productId, ProductRequestDto productRequestDto) {
        Product product = productRepository.findProduct(productId);
        if (product == null) {
            throw new IllegalArgumentException();
        }

        product.update(
                productRequestDto.getName(),
                productRequestDto.getPrice(),
                productRequestDto.getImageUrl()
        );

        return new ProductResponseDto(product);
    }

    public void deleteProduct(long productId) {

        if (productRepository.deleteProduct(productId) == null) {
            throw new IllegalArgumentException();
        }
    }

    public List<ProductResponseDto> findAllProducts() {

        return productRepository.findAllProducts()
                                .stream()
                                .map(ProductResponseDto::new)
                                .toList();
    }
}
