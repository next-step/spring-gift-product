package gift.service;

import gift.dto.ProductRequestDto;
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

    public ProductResponseDto updateProduct(Long id, String name, Integer price, String url) {
        Product product = productRepository.findProductById(id);

        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }
        product.update(name, price, url);

        return new ProductResponseDto(product);
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findProductById(id);

        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        productRepository.deleteProduct(id);

    }
}
