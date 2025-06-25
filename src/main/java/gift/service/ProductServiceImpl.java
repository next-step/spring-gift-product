package gift.service;

import gift.dto.CreateProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import gift.repository.ProductRepository;
import gift.repository.ProductRepositoryImpl;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private Long id = 1L;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponseDto createProduct(CreateProductRequestDto requestDto) {
        Product newProduct = new Product(id++, requestDto.getName(), requestDto.getPrice(),
                requestDto.getImageUrl());
        Product createdProduct = productRepository.createProduct(newProduct);
        return new ProductResponseDto(createdProduct.getId(), createdProduct.getName(),
                createdProduct.getPrice(), createdProduct.getImageUrl());
    }

    @Override
    public List<ProductResponseDto> findAllProducts() {
        return productRepository.findAllProducts();
    }

    @Override
    public ProductResponseDto findProductById(Long id) {
        Product find = productRepository.findProductById(id);

        if (find == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        ProductResponseDto responseDto = new ProductResponseDto(find.getId(), find.getName(),
                find.getPrice(), find.getImageUrl());
        return responseDto;
    }

    @Override
    public ProductResponseDto updateProductById(Long id, CreateProductRequestDto requestDto) {
        Product product = productRepository.findProductById(id);
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        product.update(requestDto);
        return new ProductResponseDto(product.getId(), product.getName(), product.getPrice(),
                product.getImageUrl());
    }

    @Override
    public void deleteProductById(Long id) {
        Product product = productRepository.findProductById(id);
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        productRepository.deleteProductById(id);
    }
}
