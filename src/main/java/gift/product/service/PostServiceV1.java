package gift.product.service;

import gift.product.dto.ProductCreateRequest;
import gift.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class PostServiceV1 implements ProductService{

    private final ProductRepository productRepository;

    public PostServiceV1(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public String addProduct(ProductCreateRequest dto) {
        return productRepository.addProduct(dto);
    }
}
