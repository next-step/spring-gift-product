package gift.service;

import gift.dto.AddProductRequestDto;
import gift.dto.AddProductResponseDto;
import gift.dto.FindProductResponseDto;
import gift.repository.ProductRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    
    private final ProductRepository productRepository;
    
    //생성자 주입
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    //상품 추가 Service
    @Override
    public AddProductResponseDto addProduct(AddProductRequestDto requestDto) {
        return productRepository.addProduct(requestDto);
    }
    
    @Override
    public List<FindProductResponseDto> findAllProducts() {
        return productRepository.findAllProducts();
    }
    
}
