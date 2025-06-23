package gift.service;

import gift.dto.ProductRequest;
import gift.dto.ProductResponse;
import gift.entity.Product;
import gift.exception.InvalidProductException;
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
        return ProductResponse.from(savedProduct);
    }

    public ProductResponse updateProduct(Long id, ProductRequest request) {
        productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 상품을 찾을 수 없습니다."));

        validate(request);

        Product updateParam = request.toEntity();
        productRepository.update(id, updateParam);

        Product updatedProduct = productRepository.findById(id).get();
        return ProductResponse.from(updatedProduct);
    }

    public List<ProductResponse> findAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(ProductResponse::from)
                .collect(Collectors.toList());
    }

    public ProductResponse findProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 상품을 찾을 수 없습니다."));

        return ProductResponse.from(product);
    }

    private void validate(ProductRequest request) {
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new InvalidProductException("상품 이름은 필수입니다.");
        }

        if (request.getPrice() <= 0) {
            throw new InvalidProductException("상품 가격은 1원 이상이어야 합니다.");
        }

        if (request.getImageURL() == null || request.getImageURL().trim().isEmpty()) {
            throw new InvalidProductException("상품 이미지 URL은 필수입니다.");
        }


    }
}