package gift.service;

import gift.dto.ProductResponseDto;
import gift.entity.Product;
import gift.exception.ProductNotFoundException;
import gift.repository.ProductRepositoryImpl;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepositoryImpl productRepositoryImpl;

    public ProductServiceImpl(ProductRepositoryImpl productRepositoryImpl) {
        this.productRepositoryImpl = productRepositoryImpl;
    }

    @Override
    public ProductResponseDto addProduct(String name, Long price, String url) {
        Product product = productRepositoryImpl.addProduct(name, price, url);
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
    public ProductResponseDto updateProduct(Long id, String name, Long price, String url) {
        Product product = productRepositoryImpl.findProductById(id);
        if (product == null) {
            throw new ProductNotFoundException(id);
        }
        Product newProduct = productRepositoryImpl.updateProduct(id, name, price, url);
        return new ProductResponseDto(newProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepositoryImpl.findProductById(id);
        if (product == null) {
            throw new ProductNotFoundException(id);
        }
        productRepositoryImpl.deleteProduct(product.id());
    }
}
