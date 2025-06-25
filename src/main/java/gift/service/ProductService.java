package gift.service;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import gift.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductService {
    private Long id = 1L;
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponseDto findProductById(Long productId) {
        Product product = productRepository.findById(productId);
        if (product == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Product not found.");
        return new ProductResponseDto(product.id(), product.name(), product.price(), product.imageUrl());
    }

    public ProductResponseDto saveProduct(ProductRequestDto dto) {
        Product product = new Product(
                id++,
                dto.name(),
                dto.price(),
                dto.imageUrl()
        );
        Product savedProduct = productRepository.saveProduct(product);
        return new ProductResponseDto(savedProduct.id(), savedProduct.name(), product.price(), product.imageUrl());
    }

    public ProductResponseDto updateProduct(Long productId, ProductRequestDto dto) {
        Product product = productRepository.findById(productId);
        if(product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Product not found.");
        }
        Product updatedProduct = new Product(productId, dto.name(), dto.price(), dto.imageUrl());
        Product savedProduct = productRepository.updateProduct(productId,updatedProduct);
        return new ProductResponseDto(savedProduct.id(), savedProduct.name(), savedProduct.price(), savedProduct.imageUrl());
    }
    }
