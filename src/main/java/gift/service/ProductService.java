package gift.service;

import gift.domain.Product;
import gift.dto.ProductMapper;
import gift.dto.ProductRequest;
import gift.dto.ProductResponse;
import gift.exception.ErrorCode;
import gift.exception.InvalidProductException;
import gift.repository.ProductRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductService{

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponse getProductById(Long productId) {
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new InvalidProductException(ErrorCode.NOT_EXISTS_PRODUCT));

        return ProductResponse.of(product.getName(), product.getPrice(), product.getImageUrl());
    }

    public Long insert(ProductRequest request) {
        validateRequest(request);

        Long id = productRepository.insert(ProductMapper.toEntity(request));
        return id;
    }

    public void update(ProductRequest request) {
        if(request==null || request.id()==null)
            throw new InvalidProductException(ErrorCode.INVALID_PRODUCT_UPDATE_REQUEST);

        productRepository.findById(request.id())
            .orElseThrow(() -> new InvalidProductException(ErrorCode.NOT_EXISTS_PRODUCT));

        productRepository.update(request);
    }

    public void deleteById(Long productId) {
        productRepository.deleteById(productId);
    }

    public List<ProductResponse> getProductList() {
        return productRepository.findAll().stream()
            .map(ProductMapper::toResponse)
            .toList();
    }

    private void validateRequest(ProductRequest request) {
        if (request == null) {
            throw new InvalidProductException(ErrorCode.NOT_EXISTS_PRODUCT);
        }

        if (request.name() == null || request.imageUrl() == null) {
            throw new InvalidProductException(ErrorCode.INVALID_PRODUCT_REQUEST);
        }

        if (request.price() < 0) {
            throw new InvalidProductException(ErrorCode.INVALID_PRODUCT_PRICE);
        }
    }
}
