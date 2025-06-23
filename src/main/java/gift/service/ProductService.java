package gift.service;

import gift.domain.Product;
import gift.dto.product.CreateProductRequest;
import gift.repository.ProductRepository;
import org.springframework.stereotype.Service;

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
}
