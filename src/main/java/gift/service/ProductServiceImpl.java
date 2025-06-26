package gift.service;

import gift.dto.RequestDto;
import gift.dto.ResponseDto;
import gift.entity.Product;
import gift.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseDto saveProduct(RequestDto requestDto) {

        Product product = new Product(requestDto.name(), requestDto.price(), requestDto.imageUrl());

        Product savedProduct = productRepository.saveProduct(product);

        return null;
    }
}
