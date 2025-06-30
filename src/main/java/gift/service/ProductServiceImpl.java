package gift.service;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import gift.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

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

    @Override
    public ProductResponseDto findProductById(Long id) {
        Product product = productRepository.findProductById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,  "해당 ID의 상품이 없습니다."));
        return new ProductResponseDto(product);
    }

    @Override
    public List<ProductResponseDto> findAllProducts() {
        List<Product> allProducts = productRepository.findAllProducts();

        return allProducts.stream()
            .map(ProductResponseDto::new)
            .toList();
    }

    @Override
    public void updateProduct(Long id, ProductRequestDto requestDto) {
        Product product = productRepository.findProductById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,  "해당 ID의 상품이 없습니다."));
        product.update(requestDto);
        productRepository.updateProduct(product);
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.findProductById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,  "해당 ID의 상품이 없습니다."));
        productRepository.deleteProductById(id);
    }

    @Override
    public void deleteAllProducts() {
        productRepository.deleteAllProducts();
    }
}
