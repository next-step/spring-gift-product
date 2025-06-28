package gift.service;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import gift.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductResponseDto> findAllProducts() {
        List<Product> products = productRepository.findAllProducts();
        return products.stream()
                .map(ProductResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponseDto saveProduct(ProductRequestDto dto) {
        Product product = new Product(dto.name(), dto.price(), dto.imageUrl());
        Product savedProduct = productRepository.saveProduct(product);
        return new ProductResponseDto(savedProduct);
    }

    @Override
    public ProductResponseDto findProductById(Long id) {
        Product product = productRepository.findProductById(id);

        return new ProductResponseDto(product);
    }

    @Override
    public ProductResponseDto updateProduct(Long id, ProductRequestDto dto) {
        Product updatedProduct = productRepository.updateProduct(id, dto.name(), dto.price(), dto.imageUrl());
        return new ProductResponseDto(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteProduct(id);

    }

}
