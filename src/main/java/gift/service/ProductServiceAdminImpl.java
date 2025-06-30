package gift.service;

import gift.domain.Product;
import gift.dto.ProductRequest;
import gift.exception.ErrorCode;
import gift.exception.InvalidProductException;
import gift.repository.ProductRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceAdminImpl implements ProductServiceAdmin {

    private final ProductRepository productRepository;

    public ProductServiceAdminImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getProductByIdAdmin(Long productId) {
        return productRepository.findById(productId)
            .orElseThrow(() -> new InvalidProductException(ErrorCode.NOT_EXISTS_PRODUCT));
    }

    @Override
    public List<Product> getProductListAdmin() {
        return productRepository.findAll();
    }

    @Override
    public void saveAdmin(Product product) {
        productRepository.save(product);
    }

    @Override
    public void updateAdmin(ProductRequest request) {
        if (request == null || request.id() == null) {
            throw new InvalidProductException(ErrorCode.INVALID_PRODUCT_UPDATE_REQUEST);
        }

        productRepository.findById(request.id())
            .orElseThrow(() -> new InvalidProductException(ErrorCode.NOT_EXISTS_PRODUCT));

        productRepository.update(request);
    }

    @Override
    public void deleteByIdAdmin(Long productId) {
        productRepository.findById(productId)
            .orElseThrow(() -> new InvalidProductException(ErrorCode.NOT_EXISTS_PRODUCT));

        productRepository.deleteById(productId);
    }

}
