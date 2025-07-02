package gift.domain.product.service;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import gift.common.pagination.Page;
import gift.common.pagination.Pageable;
import gift.domain.product.dto.ProductRequest;
import gift.domain.product.dto.ProductResponse;
import gift.domain.product.model.Product;
import gift.domain.product.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
    
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<ProductResponse> getAllProducts(Pageable pageable) {
        return productRepository.find(pageable).map(ProductResponse::from);
    }

    @Override
    public ProductResponse getProductById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new NoSuchElementException("상품을 찾을 수 없습니다.");
        }
        Product product = productRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return ProductResponse.from(product);
    }

    @Override
    public ProductResponse addProduct(ProductRequest productRequest) {
        Product product = new Product(null, productRequest.getName(), productRequest.getPrice(), productRequest.getImageUrl());
        Product savedProduct = productRepository.save(product);
        return ProductResponse.from(savedProduct);
    }

    @Override
    public void updateProduct(Long id, ProductRequest productRequest) {
        if (!productRepository.existsById(id)) {
            throw new NoSuchElementException("상품을 찾을 수 없습니다.");
        }
        Product updatedProduct = new Product(id, productRequest.getName(), productRequest.getPrice(), productRequest.getImageUrl());
        productRepository.save(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new NoSuchElementException("상품을 찾을 수 없습니다.");
        }
        productRepository.deleteById(id);
    }
}
