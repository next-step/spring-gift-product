package gift.service;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import gift.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public ProductResponseDto createProduct(ProductRequestDto productRequestDto){
        Product product = new Product();
        product.setName(productRequestDto.getName());
        product.setPrice(productRequestDto.getPrice());
        product.setImageUrl(productRequestDto.getImageUrl());

        return productRepository.createProduct(product);
    }

    public List<ProductResponseDto> searchAllProducts(){
        return productRepository.searchAllProducts();
    }

    public ProductResponseDto searchProductById(Long id){
        Optional<Product> optionalProduct = productRepository.searchProductById(id);

        Product product = optionalProduct.orElseThrow(() ->
                new NoSuchElementException("해당 ID의 상품이 존재하지 않습니다.")
        );

        return new ProductResponseDto(product);
    }

    public ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto){

        Product updated = productRepository.updateProduct(
                id,
                productRequestDto.getName(),
                productRequestDto.getPrice(),
                productRequestDto.getImageUrl()
        );

        return new ProductResponseDto(updated);
    }

    public void deleteProduct(Long id){
        productRepository.deleteProduct(id);
    }
}
