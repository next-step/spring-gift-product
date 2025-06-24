package gift.service;

import java.util.List;

import org.springframework.stereotype.Service;

import gift.dto.CreateProductRequest;
import gift.dto.CreateProductResponse;
import gift.dto.ReadProductResponse;
import gift.dto.UpdateProductRequest;
import gift.dto.UpdateProductResponse;
import gift.exception.NotFoundException;
import gift.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ReadProductResponse> getAllProducts() {
        return productRepository.findAll()
            .stream()
            .map(ReadProductResponse::of)
            .toList();
    }

    public ReadProductResponse getProductById(Long id) {
        return ReadProductResponse.of(
            productRepository.findById(id).orElseThrow(() -> new NotFoundException("해당 상품이 존재하지 않습니다.")));
    }

    public CreateProductResponse createProduct(CreateProductRequest request) {
        return CreateProductResponse.of(
            productRepository.save(request.name(), request.price(), request.imageUrl()));
    }

    public UpdateProductResponse updateProduct(Long id, UpdateProductRequest request) {
        return UpdateProductResponse.of(
            productRepository.update(id, request.name(), request.price(), request.imageUrl()));
    }

    public void deleteProduct(Long id) {
        productRepository.delete(id);
    }
}
