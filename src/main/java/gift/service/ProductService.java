package gift.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import gift.domain.Product;
import gift.dto.CreateProductRequest;
import gift.dto.CreateProductResponse;
import gift.dto.ProductResponse;
import gift.dto.UpdateProductRequest;
import gift.dto.UpdateProductResponse;
import gift.exception.ProductCreateException;
import gift.exception.ProductNotFoundException;
import gift.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final static AtomicLong currentId = new AtomicLong(1);

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
            .stream()
            .map(ProductResponse::from)
            .toList();
    }

    public ProductResponse getProductById(Long id) {
        return ProductResponse.from(
            productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("해당 상품이 존재하지 않습니다.")));
    }

    public CreateProductResponse createProduct(CreateProductRequest request) {
        Product product = Product.of(currentId.getAndIncrement(), request.name(), request.price(),
            request.imageUrl());
        productRepository.save(product);

        return CreateProductResponse.from(
            productRepository.findById(currentId.get() - 1)
                .orElseThrow(() -> new ProductCreateException("상품 생성을 실패했습니다.")));
    }

    public UpdateProductResponse updateProduct(Long id, UpdateProductRequest request) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new ProductNotFoundException("해당 상품이 존재하지 않습니다."));

        Product updatedProduct = product.createUpdatedProduct(request.name(), request.price(),
            request.imageUrl());
        productRepository.save(updatedProduct);

        return UpdateProductResponse.from(updatedProduct);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
