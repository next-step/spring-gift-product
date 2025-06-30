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
        Product product = new Product(null, productRequestDto.name(),
                productRequestDto.price(), productRequestDto.imageUrl());

        Product savedProduct = productRepository.saveProduct(product);

        return ProductResponseDto.from(savedProduct);
    }

    public ProductResponseDto findProduct(Long productId) {
        Product product = findProductOrThrow(productId);

        return ProductResponseDto.from(product);
    }

    public ProductResponseDto updateProduct(Long productId, ProductRequestDto productRequestDto) {
        Product product = findProductOrThrow(productId);
        product.update(
                productRequestDto.name(),
                productRequestDto.price(),
                productRequestDto.imageUrl()
        );

        return ProductResponseDto.from(product);
    }

    public void deleteProduct(Long productId) {
        findProductOrThrow(productId);

        productRepository.deleteProduct(productId);
    }

    public List<ProductResponseDto> findAllProducts() {

        return productRepository.findAllProducts()
                                .stream()
                                .map(ProductResponseDto::from)
                                .toList();
    }

    private Product findProductOrThrow(Long productId) {
        return productRepository.findProduct(productId)
                                .orElseThrow(IllegalArgumentException::new);
    }
}
