package gift.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gift.domain.Product;
import gift.dto.CreateProductRequest;
import gift.dto.CreateProductResponse;
import gift.dto.ProductResponse;
import gift.dto.UpdateProductRequest;
import gift.dto.UpdateProductResponse;
import gift.exception.ProductCreateException;
import gift.exception.ProductDeleteException;
import gift.exception.ProductNotFoundException;
import gift.exception.ProductUpdateException;
import gift.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
            .stream()
            .map(ProductResponse::from)
            .toList();
    }

    @Transactional(readOnly = true)
    public ProductResponse getProductById(Long id) {
        return ProductResponse.from(
            productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("해당 상품이 존재하지 않습니다.")));
    }

    @Transactional
    public CreateProductResponse createProduct(CreateProductRequest request) {
        Product product = Product.of(request.name(), request.price(), request.imageUrl());

        Long generatedId = productRepository.save(product);

        return CreateProductResponse.from(
            productRepository.findById(generatedId)
                .orElseThrow(() -> new ProductCreateException("상품 생성을 실패했습니다.")));
    }

    @Transactional
    public UpdateProductResponse updateProduct(Long id, UpdateProductRequest request) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("해당 상품이 존재하지 않습니다.");
        }

        Product newProduct = Product.of(id, request.name(), request.price(), request.imageUrl());

        int count = productRepository.update(newProduct);
        if (count != 1) {
            throw new ProductUpdateException("상품 수정을 실패했습니다.");
        }

        return UpdateProductResponse.from(newProduct);
    }

    @Transactional
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("해당 상품이 존재하지 않습니다.");
        }

        int count = productRepository.delete(id);
        if (count != 1) {
            throw new ProductDeleteException("상품 삭제를 실패했습니다.");
        }
    }
}
