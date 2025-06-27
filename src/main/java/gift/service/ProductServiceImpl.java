package gift.service;

import gift.dto.api.AddProductRequestDto;
import gift.dto.api.ModifyProductRequestDto;
import gift.dto.api.ProductResponseDto;
import gift.entity.Product;
import gift.repository.ProductRepository;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductServiceImpl implements ProductService {
    
    private final ProductRepository productRepository;
    
    //생성자 주입
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    //상품 추가 Service
    @Override
    public ProductResponseDto addProduct(AddProductRequestDto requestDto) {
        
        if (requestDto.isNotValid()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        
        Product product = new Product(
            0L,
            requestDto.getName(),
            requestDto.getPrice(),
            requestDto.getImageUrl()
        );
        
        return productRepository.addProduct(product);
    }
    
    //상품 전체 조회
    @Override
    public List<ProductResponseDto> findAllProducts() {
        return productRepository.findAllProducts();
    }
    
    //상품 단건 조회
    @Override
    public ProductResponseDto findProductWithId(Long id) {
        Product product = productRepository.findProductWithId(id);
        return new ProductResponseDto(product);
    }
    
    //상품 수정 (상품 자체가 다른 것으로 바뀜)
    @Override
    public ProductResponseDto modifyProductWithId(Long id,
        ModifyProductRequestDto requestDto) {
        Product product = productRepository.findProductWithId(id);
        
        if (requestDto.isNotValidForModify()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        
        Product newProduct = new Product(
            id,
            requestDto.getName(),
            requestDto.getPrice(),
            requestDto.getImageUrl()
        );
        
        return productRepository.modifyProductWithId(id,
            newProduct);
    }
    
    //상품 단건 삭제
    @Override
    public void deleteProductWithId(Long id) {
        Product product = productRepository.findProductWithId(id);
        productRepository.deleteProductWithId(id);
    }
    
    @Override
    public ProductResponseDto modifyProductInfoWithId(Long id,
        ModifyProductRequestDto requestDto) {
        Product product = productRepository.findProductWithId(id);
        
        if (requestDto.isNotValidForModifyInfo()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        
        Product newProduct = new Product(
            id,
            requestDto.getName() != null ? requestDto.getName() : product.getName(),
            requestDto.getPrice() != null ? requestDto.getPrice() : product.getPrice(),
            requestDto.getImageUrl() != null ? requestDto.getImageUrl() : product.getImageUrl()
        );
        
        return productRepository.modifyProductWithId(id,
            newProduct);
    }
    
}
