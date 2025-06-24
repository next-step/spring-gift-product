package gift.service;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import gift.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public ProductResponseDto saveProduct(ProductRequestDto requestDto) {
        Product product = new Product( requestDto.name(), requestDto.price(), requestDto.imageUrl());
        Product savedProduct = productRepository.saveProduct(product);

        return new ProductResponseDto(savedProduct);
    }

    public List<ProductResponseDto> findAllProducts() {
        return productRepository.findAllProducts();
    }

    public ProductResponseDto findProductById(Long id) {
        Product product = productRepository.findProductById(id);
        return new ProductResponseDto(product);
    }
}
