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
public class ProductServiceAdminImpl implements ProductService{

    private final ProductRepository productRepository;

    public ProductServiceAdminImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // 일반 사용자용 메서드
    @Override
    public ProductResponse getProductById(Long productId) {
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new InvalidProductException(ErrorCode.NOT_EXISTS_PRODUCT));

        return ProductResponse.of(product.getName(), product.getPrice(), product.getImageUrl());
    }

    @Override
    public void save(ProductRequest request) {
        validateRequest(request);

        productRepository.save(ProductMapper.toEntity(request));
    }

    @Override
    public void update(ProductRequest request) {
        if(request==null || request.id()==null)
            throw new InvalidProductException(ErrorCode.INVALID_PRODUCT_UPDATE_REQUEST);

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
            .map(ProductMapper::toResponse)
            .toList();
    }


    // admin용 메서드
    public Product getProductByIdAdmin(Long productId) {
        return productRepository.findById(productId)
            .orElseThrow(() -> new InvalidProductException(ErrorCode.NOT_EXISTS_PRODUCT));
    }

    public List<Product> getProductListAdmin() {
        return productRepository.findAll();
    }

    public void saveAdmin(Product product){
        productRepository.save(product);
    }



    // 검증용 메서드
    private void validateRequest(ProductRequest request) {
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
