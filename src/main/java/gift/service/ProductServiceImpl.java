package gift.service;

import gift.dto.CreateProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import gift.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponseDto createProduct(CreateProductRequestDto requestDto) {
        Product newProduct = new Product(null, requestDto.name(), requestDto.price(),
                requestDto.imageUrl());
        Product savedProduct = productRepository.createProduct(newProduct);
        return new ProductResponseDto(savedProduct.getId(), savedProduct.getName(),
                savedProduct.getPrice(), savedProduct.getImageUrl());
    }

    @Override
    public List<ProductResponseDto> findAllProducts() {

        List<Product> products = productRepository.findAllProducts();
        List<ProductResponseDto> productsList = new ArrayList<>();
        for (Product product : products) {
            ProductResponseDto responseDto = new ProductResponseDto(product.getId(),
                    product.getName(), product.getPrice(), product.getImageUrl());
            productsList.add(responseDto);
        }
        return productsList;
    }

    @Override
    public ProductResponseDto findProductById(Long id) {
        Product find = findProductByIdOrElseThrow(id);
        ProductResponseDto responseDto = new ProductResponseDto(find.getId(), find.getName(),
                find.getPrice(), find.getImageUrl());
        return responseDto;
    }

    @Override
    public ProductResponseDto updateProductById(Long id, CreateProductRequestDto requestDto) {
        Product find = findProductByIdOrElseThrow(id);
        Product newProduct = new Product(id, requestDto.name(), requestDto.price(),
                requestDto.imageUrl());
        productRepository.updateProductById(id, newProduct);
        Product updated = findProductByIdOrElseThrow(id);
        return new ProductResponseDto(updated.getId(), updated.getName(), updated.getPrice(),
                updated.getImageUrl());
    }

    @Override
    public void deleteProductById(Long id) {
        findProductByIdOrElseThrow(id);
        productRepository.deleteProductById(id);
    }

    public Product findProductByIdOrElseThrow(Long id) {
        return productRepository.findProductById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
