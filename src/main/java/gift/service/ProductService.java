package gift.service;

import gift.dto.ProductRequest;
import gift.dto.ProductResponse;
import gift.entity.Product;
import gift.exception.InvalidProductException;
import gift.repository.ProductRepository;
import org.springframework.stereotype.Service;


@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponse addProduct(ProductRequest request) {
        validate(request); // 유효성 검사

        Product product = request.toEntity();
        Product savedProduct = productRepository.save(product);
        return ProductResponse.from(savedProduct);
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