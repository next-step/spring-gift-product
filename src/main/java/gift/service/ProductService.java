package gift.service;

import gift.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    
    // 의존성 고정
    private final ProductRepository productRepository;

    // 의존성 주입
    public ProductService(ProductRepository productRepository) {this.productRepository = productRepository;}
}
