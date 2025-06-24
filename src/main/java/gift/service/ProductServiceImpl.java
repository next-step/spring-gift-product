package gift.service;

import gift.dto.CreateProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import gift.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{
    private ProductRepository productRepository;
    private Long id = 1L;

    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponseDto createProduct(CreateProductRequestDto requestDto) {
        Product newProduct = new Product(id++, requestDto.getName(),requestDto.getPrice(),requestDto.getImageUrl());
        Product createdProduct = productRepository.createProduct(newProduct);
        return new ProductResponseDto(createdProduct.getId(), createdProduct.getName(), createdProduct.getPrice(), createdProduct.getImageUrl());
    }
}
