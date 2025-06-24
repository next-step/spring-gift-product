package gift.service;

import gift.dto.ProductResponseDto;
import gift.repository.ProductRepository;
import gift.repository.ProductRepositoryImp;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepositoryImp productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductResponseDto> findAllProducts(){

        return productRepository.findAllProducts();
    }
}
