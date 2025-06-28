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
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void addProduct(ProductAddRequestDto requestDto) {
        Product product = new Product(null, requestDto.name(), requestDto.price(), requestDto.url());
        int result = productRepository.addProduct(product);
    }

    @Override
    public ProductResponseDto findProductById(Long id) {
        Product product = productRepository.findProductByIdOrElseThrow(id);
        return new ProductResponseDto(product);
    }

    @Override
    public List<ProductResponseDto> findAllProduct() {
        List<Product> products = productRepository.findAllProduct();
        List<ProductResponseDto> responseDtos = products.stream().map(Product::toProductResponseDto).toList();
        return responseDtos;
    }

    @Override
    public void updateProductById(Long id, ProductUpdateRequestDto requestDto) {
        Product product = productRepository.findProductByIdOrElseThrow(id);
        Product newProduct = new Product(id, requestDto);
        int result = productRepository.updateProductById(newProduct); //1 -> 성공, 0 -> 실패
    }

    @Override
    public void deleteProductById(Long id) {
        Product product = productRepository.findProductByIdOrElseThrow(id);
        int result = productRepository.deleteProductById(product.id());
    }
}
