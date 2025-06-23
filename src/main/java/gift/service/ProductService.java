package gift.service;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import gift.repository.ProductRepository;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) {
        Product product = new Product(
                productRequestDto.getName(),
                productRequestDto.getPrice(),
                productRequestDto.getImageUrl()
        );

        Product saveProduct = productRepository.addProduct(product);
        return new ProductResponseDto(
                saveProduct.getId(),
                saveProduct.getName(),
                saveProduct.getPrice(),
                saveProduct.getImageUrl()
        );
    }

    public ProductResponseDto findProductById(Long id) {
        Product product = productRepository.findProductById(id);
        if (product == null) {
            throw new NoSuchElementException("Invalid id = " + id);
        }
        return new ProductResponseDto(
            product.getId(),
            product.getName(),
            product.getPrice(),
            product.getImageUrl()
        );
    }

    public ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto) {
        Product product = productRepository.findProductById(id);
        if (product == null) {
            throw new NoSuchElementException("Invalid id = " + id);
        }
        product.updateProduct(productRequestDto.getName(), productRequestDto.getPrice(), productRequestDto.getImageUrl());
        Product updateProduct = productRepository.updateProduct(product);

        return new ProductResponseDto(
            updateProduct.getId(),
            updateProduct.getName(),
            updateProduct.getPrice(),
            updateProduct.getImageUrl()
        );
    }


}
