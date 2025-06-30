package gift.service;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import gift.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponseDto> findAllProducts() {
        List<Product> products = productRepository.findAllProducts();
        return products.stream()
                .map(ProductResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProductResponseDto saveProduct(ProductRequestDto dto) {
        Product product = new Product(dto.name(), dto.price(), dto.imageUrl());
        Product savedProduct = productRepository.saveProduct(product);
        return new ProductResponseDto(savedProduct);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponseDto findProductById(Long id) {
        Product product = productRepository.findProductById(id);
        return new ProductResponseDto(product);
    }

    @Override
    @Transactional
    public ProductResponseDto updateProduct(Long id, ProductRequestDto dto) {
        productRepository.updateProduct(id, dto.name(), dto.price(), dto.imageUrl());
        Product updatedProduct = productRepository.findProductById(id);
        return new ProductResponseDto(updatedProduct);
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteProduct(id);
    }
}
