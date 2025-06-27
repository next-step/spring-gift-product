package gift.service;

import gift.dto.CreateProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import gift.repository.ProductRepository;
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
        return productRepository.findAllProducts();
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
        find.update(requestDto);
        return new ProductResponseDto(find.getId(), find.getName(), find.getPrice(),
                find.getImageUrl());
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteProductById(id);
    }

    public Product findProductByIdOrElseThrow(Long id){
        return productRepository.findProductById(id).orElseThrow(() ->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
