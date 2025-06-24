package gift.service;

import gift.domain.Product;
import gift.dto.ProductMapper;
import gift.dto.ProductRequest;
import gift.dto.ProductResponse;
import gift.exception.ErrorCode;
import gift.exception.InvalidProductException;
import gift.repository.ProductRepositoryImpl;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepositoryImpl productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepositoryImpl productRepository,
        ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ProductResponse getProductById(Long productId) {
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new InvalidProductException(ErrorCode.NOT_EXISTS_PRODUCT));

        return ProductResponse.of(product.getName(), product.getPrice(), product.getImageUrl());
    }

    @Override
    public void save(ProductRequest request) {
        validateRequest(request);

        productRepository.save(productMapper.toEntity(request));
    }

    @Override
    public void update(ProductRequest request) {
        validateRequest(request);

        Product product = productRepository.findById(request.id())
            .orElseThrow(() -> new InvalidProductException(ErrorCode.NOT_EXISTS_PRODUCT));

        product.update(request.name(), request.price(), request.imageUrl());
        productRepository.save(product);
    }

    @Override
    public void deleteById(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public List<ProductResponse> getProductList() {
        return productRepository.findAll().stream()
            .map(productMapper::toResponse)
            .toList();
    }

    public void validateRequest(ProductRequest request) {
        if (request == null || request.id() == null) {
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
