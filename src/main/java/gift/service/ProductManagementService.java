package gift.service;

import gift.domain.Product;
import gift.dto.ProductRequest;
import gift.dto.ProductResponse;
import gift.dto.common.Page;
import gift.exception.BusinessException;
import gift.exception.ErrorCode;
import gift.repository.ProductRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductManagementService {

    private final ProductRepository productRepository;

    public ProductManagementService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public ProductResponse create(ProductRequest request) {
        Product newProduct = Product.of(
                request.name(),
                request.validatedPrice(),
                request.imageUrl()
        );
        Product savedProduct = productRepository.save(newProduct);
        return ProductResponse.from(savedProduct);
    }

    public Page<ProductResponse> getAllByPage(int pageNumber, int pageSize) {
        Page<Product> productPage = productRepository.findAllByPage(pageNumber, pageSize);

        List<ProductResponse> responseContent = productPage.content().stream()
                .map(ProductResponse::from)
                .toList();

        return new Page<>(
                responseContent,
                productPage.page(),
                productPage.size(),
                productPage.totalElements()
        );
    }

    public ProductResponse getById(Long id) {
        Product foundProduct = productRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.PRODUCT_NOT_FOUND));
        return ProductResponse.from(foundProduct);
    }

    @Transactional
    public void update(Long id, ProductRequest request) {
        if (productRepository.findById(id).isEmpty()) {
            throw new BusinessException(ErrorCode.PRODUCT_NOT_FOUND);
        }
        Product updatedProduct = Product.of(
                request.name(),
                request.validatedPrice(),
                request.imageUrl()
        );
        productRepository.update(id, updatedProduct);
    }

    @Transactional
    public void deleteAllByIds(List<Long> ids) {
        productRepository.deleteAllByIds(ids);
    }

    @Transactional
    public void deleteById(Long id) {
        if (productRepository.findById(id).isEmpty()) {
            throw new BusinessException(ErrorCode.PRODUCT_NOT_FOUND);
        }
        productRepository.deleteById(id);
    }
}

