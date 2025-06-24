package gift.service;

import gift.dto.ProductAddResponseDto;
import gift.entity.Product;
import gift.repository.ProductRepositoryImpl;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepositoryImpl productRepositoryImpl;

    public ProductServiceImpl(ProductRepositoryImpl productRepositoryImpl) {
        this.productRepositoryImpl = productRepositoryImpl;
    }

    @Override
    public ProductAddResponseDto addProduct(String name, Long price, String url) {
        Product product = productRepositoryImpl.addProduct(name, price, url);
        return new ProductAddResponseDto(product);
    }
}
