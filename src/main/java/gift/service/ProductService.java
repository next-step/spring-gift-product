package gift.service;

import gift.domain.Product;
import gift.dto.product.CreateProductRequest;
import gift.dto.product.ProductResponse;
import gift.dto.product.UpdateProductRequest;
import gift.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product saveProduct(CreateProductRequest request) {
        Product product = Product.of(request);
        return productRepository.save(product);
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(ProductResponse::new).toList();
    }

    public Product updateProduct(Long id, UpdateProductRequest request) {
        Optional<Product> getProduct = productRepository.findById(id);
        Product product = getProduct.orElseThrow(() -> new IllegalStateException("Product를 찾을 수 없습니다."));
        return product.update(request);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteByid(id);
    }
}
