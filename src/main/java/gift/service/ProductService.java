package gift.service;

import gift.dto.ProductResponseDto;
import gift.entity.Product;
import gift.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponseDto createProduct(String name, Integer price, String imageUrl) {
        Product product = new Product(name, price, imageUrl);
        Product newProduct = productRepository.save(product);

        return new ProductResponseDto(newProduct.getId(), newProduct.getName(), newProduct.getPrice(), newProduct.getImageUrl());
    }

}
