package gift.service;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import gift.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponseDto saveProduct(ProductRequestDto requestDto) {
        Product product = new Product(requestDto.getName(), requestDto.getPrice(), requestDto.getImageUrl());
        Product savedProduct = productRepository.saveProduct(product);
        return new ProductResponseDto(savedProduct);
    }
}
