package gift.service;

import gift.dto.AddProductRequestDto;
import gift.dto.AddProductResponseDto;
import gift.dto.FindProductResponseDto;
import gift.dto.ModifyProductRequestDto;
import gift.dto.ModifyProductResponseDto;
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
    
    //상품 수정 (상품 자체가 다른 것으로 바뀜)
    @Override
    public ModifyProductResponseDto modifyProductWithDbId(Long id,
        ModifyProductRequestDto requestDto) {
        Product product = productRepository.findProductWithDbId(id);
        
        Product newProduct = new Product(
            requestDto.getId(),
            requestDto.getName(),
            requestDto.getPrice(),
            requestDto.getImageUrl()
        );
        
        ModifyProductResponseDto responseDto = productRepository.modifyProductWithDbId(id,
            newProduct);
        return responseDto;
    }
    
    @Override
    public void deleteProductWithDbId(Long id) {
        Product product = productRepository.findProductWithDbId(id);
        productRepository.deleteProductWithDbId(id);
    }
    
}
