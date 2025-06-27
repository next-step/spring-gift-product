package gift.service;

import gift.dto.ProductRequest;
import gift.dto.ProductResponse;
import gift.entity.Product;
import gift.exception.InvalidProductException;
import gift.exception.ProductNotFoundException;
import gift.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponse addProduct(ProductRequest request) {
        validate(request);
        Product product = request.toEntity();
        Product savedProduct = productRepository.save(product);
        return savedProduct.toResponse();
    }

    public ProductResponse updateProduct(Long id, ProductRequest request) {
        validate(request);
        Product product = findProductEntityById(id);

        product.updateWith(request);
        productRepository.update(product);

        return product.toResponse();
    }

    public List<ProductResponse> findAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(Product::toResponse)
                .collect(Collectors.toList());
    }

    public ProductResponse findProductById(Long id) {
        Product product = findProductEntityById(id);
        return product.toResponse();
    }

    public void deleteProduct(Long id) {
        Product product = findProductEntityById(id);
        productRepository.delete(product.getId());
    }

    public void deleteProducts(List<Long> ids) {
        ids.forEach(this::deleteProduct);
    }

    private void validate(ProductRequest request) {
        if (request.name() == null || request.name().trim().isEmpty()) {
            throw new InvalidProductException("상품 이름은 필수입니다.");
        }
        if (request.price() <= 0) {
            throw new InvalidProductException("상품 가격은 1원 이상이어야 합니다.");
        }
        if (request.imageUrl() == null || request.imageUrl().trim().isEmpty()) {
            throw new InvalidProductException("상품 이미지 URL은 필수입니다.");
        }
    }

    private Product findProductEntityById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("해당 ID의 상품을 찾을 수 없습니다: " + id));
    }
}
