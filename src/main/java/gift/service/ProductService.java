package gift.service;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import gift.exception.ProductNotExistException;
import gift.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponseDto create(ProductRequestDto requestDto) {
        Product product = new Product(requestDto.getName(), requestDto.getPrice(), requestDto.getImageUrl());
        Product newProduct = productRepository.save(product);

        return new ProductResponseDto(newProduct.getId(), newProduct.getName(), newProduct.getPrice(), newProduct.getImageUrl());
    }

    public ProductResponseDto find(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotExistException(productId)); // 상품이 없는 경우 예외 처리

        return new ProductResponseDto(product.getId(), product.getName(), product.getPrice(), product.getImageUrl());
    }

    public ProductResponseDto update(Long productId, ProductRequestDto requestDto) {
        Product product = productRepository.update(productId, requestDto.getName(), requestDto.getPrice(), requestDto.getImageUrl())
                .orElseThrow(() -> new ProductNotExistException(productId));

        return new ProductResponseDto(product.getId(), product.getName(), product.getPrice(), product.getImageUrl());
    }

    public void delete(Long productId) {
        productRepository.deleteById(productId)
                .orElseThrow(() -> new ProductNotExistException(productId));
    }

    public List<ProductResponseDto> findAll(int page, int size, String sort) {
        String[] sortParts = sort.split(",");
        String sortField = sortParts[0];
        String sortDir = sortParts[1];

        return productRepository.findAll(page, size, sortField, sortDir).stream()
                .map(p -> new ProductResponseDto(p.getId(), p.getName(), p.getPrice(), p.getImageUrl()))
                .toList();
    }
}
