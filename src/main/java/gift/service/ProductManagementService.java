package gift.service;

import gift.domain.Product;
import gift.dto.ProductRequest;
import gift.dto.common.Page;
import gift.exception.BusinessException;
import gift.exception.ErrorCode;
import gift.repository.ProductRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductManagementService {

    private final ProductRepository productRepository;

    public ProductManagementService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void create(ProductRequest request) {
        validateRequest(request);
        Product newProduct = Product.of(
                request.name(),
                request.price(),
                request.imageUrl()
        );
        productRepository.save(newProduct);
    }

    public Page<Product> getAllByPage(int pageNumber, int pageSize) {
        return productRepository.findAllByPage(pageNumber, pageSize);
    }

    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.PRODUCT_NOT_FOUND));
    }

    public void update(Long id, ProductRequest request) {
        validateRequest(request);
        if (productRepository.findById(id).isEmpty()) {
            throw new BusinessException(ErrorCode.PRODUCT_NOT_FOUND);
        }
        Product updatedProduct = Product.of(
                request.name(),
                request.price(),
                request.imageUrl()
        );
        productRepository.update(id, updatedProduct);
    }

    public void deleteAll() {
        productRepository.deleteAll();
    }

    public void deleteAllByIds(List<Long> ids) {
        productRepository.deleteAllByIds(ids);
    }

    public void deleteById(Long id) {
        if (productRepository.findById(id).isEmpty()) {
            throw new BusinessException(ErrorCode.PRODUCT_NOT_FOUND);
        }
        productRepository.deleteById(id);
    }

    private void validateRequest(ProductRequest request) {
        if (request == null
                || request.name() == null || request.name().isBlank()
                || request.price() == null) {
            throw new BusinessException(ErrorCode.INVALID_PRODUCT_DATA);
        }
    }
}

