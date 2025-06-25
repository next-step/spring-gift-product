package gift.service;

import gift.dto.ProductResponseDto;
import gift.entity.Product;
import gift.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponseDto create(String name, Integer price, String imageUrl) {
        Product product = new Product(name, price, imageUrl);
        Product newProduct = productRepository.save(product);

        return new ProductResponseDto(newProduct.getId(), newProduct.getName(), newProduct.getPrice(), newProduct.getImageUrl());
    }

    public ProductResponseDto find(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found: id=" + id)); // 상품이 없는 경우 예외 처리

        return new ProductResponseDto(product.getId(), product.getName(), product.getPrice(), product.getImageUrl());
    }

    public ProductResponseDto update(Long productId, String name, Integer price, String imageUrl) {
        Product product = productRepository.update(productId, name, price, imageUrl)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found: id=" + productId));

        return new ProductResponseDto(product.getId(), product.getName(), product.getPrice(), product.getImageUrl());
    }

    public void delete(Long productId) {
        productRepository.deleteById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found: id=" + productId));
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
