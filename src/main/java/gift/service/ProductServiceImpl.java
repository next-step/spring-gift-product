package gift.service;

import gift.dto.ProductAddRequestDto;
import gift.dto.ProductResponseDto;
import gift.dto.ProductUpdateRequestDto;
import gift.entity.Product;
import gift.exception.ProductNotFoundException;
import gift.repository.ProductRepository;
import gift.repository.ProductRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepositoryImpl productRepositoryImpl) {
        this.productRepository = productRepositoryImpl;
    }

    @Override
    public ProductResponseDto addProduct(ProductAddRequestDto requestDto) {
        Product product = productRepository.addProduct(requestDto.getName(), requestDto.getPrice(), requestDto.getUrl());
        return new ProductResponseDto(product);
    }

    @Override
    public ProductResponseDto findProductById(Long id) {
        Product product = productRepository.findProductById(id);
        if (product == null) {
            throw new ProductNotFoundException(id);
        }
        return new ProductResponseDto(product);
    }

    @Override
    public List<ProductResponseDto> findAllProduct() {
        List<Product> products = productRepository.findAllProduct();
        List<ProductResponseDto> responseDto = products.stream().map(Product::toProductResponseDto).toList();
        return responseDto;
    }

    @Override
    public ProductResponseDto updateProductById(Long id, ProductUpdateRequestDto requestDto) {
        Product product = productRepository.findProductById(id);
        if (product == null) {
            throw new ProductNotFoundException(id);
        }
        Product newProduct = new Product(product.id(), requestDto.getName(), requestDto.getPrice(), requestDto.getUrl());
        productRepository.updateProductById(newProduct);
        return new ProductResponseDto(newProduct);
    }

    @Override
    public void deleteProductById(Long id) {
        Product product = productRepository.findProductById(id);
        if (product == null) {
            throw new ProductNotFoundException(id);
        }
        productRepository.deleteProductById(product.id());
    }
}
