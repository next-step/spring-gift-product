package gift.service;

import gift.dto.ProductResponseDto;
import gift.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductResponseDto> findAllProducts() {
        List<ProductResponseDto> products = productRepository.findAllProducts();
        return products;
    }

}
