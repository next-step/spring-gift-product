package gift.service;

import gift.dto.ProductAddRequestDto;
import gift.dto.ProductResponseDto;
import gift.dto.ProductUpdateRequestDto;
import gift.entity.Product;
import gift.exception.ProductNotFoundException;
import gift.repository.ProductRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepositoryImpl productRepositoryImpl;

    public ProductServiceImpl(ProductRepositoryImpl productRepositoryImpl) {
        this.productRepositoryImpl = productRepositoryImpl;
    }

    @Override
    public ProductResponseDto addProduct(ProductAddRequestDto requestDto) {
        Product product = productRepositoryImpl.addProduct(requestDto.name(), requestDto.price(), requestDto.url());
        return new ProductResponseDto(product);
    }

    @Override
    public ProductResponseDto findProductById(Long id) {
        Product product = productRepositoryImpl.findProductById(id);
        if (product == null) {
            throw new ProductNotFoundException(id);
        }
        return new ProductResponseDto(product);
    }

    @Override
    public List<ProductResponseDto> findAllProduct() {
        List<Product> products = productRepositoryImpl.findAllProduct();
        List<ProductResponseDto> responseDto = products.stream().map(Product::toProductResponseDto).toList();
        return responseDto;
    }

    @Override
    public ProductResponseDto updateProductById(Long id, ProductUpdateRequestDto requestDto) {
        Product product = productRepositoryImpl.findProductById(id);
        if (product == null) {
            throw new ProductNotFoundException(id);
        }
        Product newProduct = new Product(product.id(), requestDto.name(), requestDto.price(), requestDto.url());
        productRepositoryImpl.updateProductById(newProduct);
        return new ProductResponseDto(newProduct);
    }

    @Override
    public void deleteProductById(Long id) {
        Product product = productRepositoryImpl.findProductById(id);
        if (product == null) {
            throw new ProductNotFoundException(id);
        }
        productRepositoryImpl.deleteProductById(product.id());
    }
}
