package gift.service;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import gift.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public ProductResponseDto addProduct(ProductRequestDto requestDto){
        Product product = requestDto.toEntity();
        Product savedProduct = productRepository.save(product);
        return new ProductResponseDto(savedProduct);
    }

    public List<ProductResponseDto> getProducts(){
        List<Product> products = productRepository.findAll();

        List<ProductResponseDto> responseDtos = new ArrayList<>();

        for(Product product : products){
            ProductResponseDto dto = new ProductResponseDto(product);
            responseDtos.add(dto);
        }
        return responseDtos;
    }

    public ProductResponseDto getProduct(Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다. id = " + id));
        return new ProductResponseDto(product);
    }

    public ProductResponseDto updateProduct(Long id, ProductRequestDto requestDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다. id=" + id));

        product.update(
                requestDto.name(),
                requestDto.price(),
                requestDto.imageUrl()
        );
        return new ProductResponseDto(product);
    }

    public void deleteProduct(Long id) {
        productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다. id=" + id));
        productRepository.deleteById(id);
    }
}

