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

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponseDto saveProduct(ProductRequestDto requestDto) {
        Product product = new Product(requestDto.getName(), requestDto.getPrice(), requestDto.getImageUrl());
        Product saveProduct = productRepository.saveProduct(product);
        System.out.println(saveProduct.getId());
        System.out.println(saveProduct.getName());
        System.out.println(saveProduct.getPrice());
        System.out.println(saveProduct.getImageUrl());

        return new ProductResponseDto(saveProduct);
    }

    @Override
    public List<ProductResponseDto> findAllProducts() {
        List<ProductResponseDto> allProducts = productRepository.findAllProducts();
        return allProducts;
    }

    @Override
    public ProductResponseDto findProductById(Long id) {
        return null;
    }

    @Override
    public ProductResponseDto updateProduct(ProductRequestDto request) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteProduct(id);
    }
}
