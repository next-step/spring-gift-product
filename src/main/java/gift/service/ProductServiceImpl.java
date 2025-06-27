package gift.service;

import gift.dto.api.AddProductRequestDto;
import gift.dto.api.AddProductResponseDto;
import gift.dto.api.FindProductResponseDto;
import gift.dto.api.ModifyProductRequestDto;
import gift.dto.api.ModifyProductResponseDto;
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
    public AddProductResponseDto addProduct(AddProductRequestDto requestDto) {
        
        if (requestDto.isNotValid()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        
        Product product = new Product(
            0L,
            requestDto.name(),
            requestDto.price(),
            requestDto.imageUrl()
        );
        
        return productRepository.addProduct(product);
    }
    
    //상품 전체 조회
    @Override
    public List<FindProductResponseDto> findAllProducts() {
        return productRepository.findAllProducts();
    }
    
    //상품 단건 조회
    @Override
    public FindProductResponseDto findProductWithId(Long id) {
        Product product = productRepository.findProductWithId(id);
        return new FindProductResponseDto(product);
    }
    
    //상품 수정 (상품 자체가 다른 것으로 바뀜)
    @Override
    public ModifyProductResponseDto modifyProductWithId(Long id,
        ModifyProductRequestDto requestDto) {
        Product product = productRepository.findProductWithId(id);
        
        if (requestDto.isNotValidForModify()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        
        Product newProduct = new Product(
            id,
            requestDto.name(),
            requestDto.price(),
            requestDto.imageUrl()
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
    public ModifyProductResponseDto modifyProductInfoWithId(Long id,
        ModifyProductRequestDto requestDto) {
        Product product = productRepository.findProductWithId(id);
        
        if (requestDto.isNotValidForModifyInfo()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        
        Product newProduct = new Product(
            id,
            requestDto.name() != null ? requestDto.name() : product.getName(),
            requestDto.price() != null ? requestDto.price() : product.getPrice(),
            requestDto.imageUrl() != null ? requestDto.imageUrl() : product.getImageUrl()
        );
        
        return productRepository.modifyProductWithId(id,
            newProduct);
    }
    
}
