package gift.service;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import gift.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductService {
    
    // 의존성 고정
    private final ProductRepository productRepository;

    // 의존성 주입
    public ProductService(ProductRepository productRepository) {this.productRepository = productRepository;}

    public ProductResponseDto findProductById(Long id){
        Product product = productRepository.findProductById();

        return new ProductResponseDto(product);
    }

    public List<ProductResponseDto> findAllProduct(){
        return productRepository.findAllProducts();
    }

    public ProductResponseDto saveProduct(ProductRequestDto requestDto){
        Product product = new Product(requestDto.name(), requestDto.price());
        return productRepository.saveProduct(product);
    }

    public ProductResponseDto updateProduct(Long id, String name, Long price){
        // 필수값 검증
        if (name == null || price == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The name and price are required values.");
        }
        boolean flag = productRepository.updateProduct(id, name, price);
        
        // 수정됐는지 검증
        if(!flag) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No product found");
        }
        
        return productRepository.findProductById(id);
    }

    public void deleteProduct(Long id){
        boolean flag = productRepository.deleteProduct(id);
        if(!flag) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No product found");
        }
    }
}
