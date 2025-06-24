package gift.service;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import gift.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductResponseDto> findAllProducts() {
        List<ProductResponseDto> products = productRepository.findAllProducts();
        return products;
    }

    @Override
    public ProductResponseDto saveProduct(ProductRequestDto dto) {
        Product product = new Product(dto.getName(), dto.getPrice(), dto.getImageUrl());
        return productRepository.saveProduct(product);
    }

    @Override
    public ProductResponseDto findProductById(Long id) {
        Product product = productRepository.findProductById(id);

        return new ProductResponseDto(product);
    }

    @Override
    public ProductResponseDto updateProduct(Long id, ProductRequestDto dto) {
        Product product = productRepository.findProductById(id);
        Product updatedProduct = productRepository.updateProduct(id, dto.getName(), dto.getPrice(), dto.getImageUrl());
        return new ProductResponseDto(updatedProduct);
    }

}
