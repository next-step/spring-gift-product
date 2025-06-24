package gift.service;

import gift.dto.AddProductRequestDto;
import gift.dto.AddProductResponseDto;
import gift.dto.FindProductResponseDto;
import gift.entity.Product;
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
    
    //상품 전체 조회
    @Override
    public List<FindProductResponseDto> findAllProducts() {
        return productRepository.findAllProducts();
    }
    
    //상품 단건 조회
    @Override
    public FindProductResponseDto findProductWithDbId(Long id) {
        Product product = productRepository.findProductWithDbId(id);
        return new FindProductResponseDto(id, product);
    }
    
}
