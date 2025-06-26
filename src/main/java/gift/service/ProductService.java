package gift.service;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import gift.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public ProductResponseDto addProduct(ProductRequestDto productRequestDto){
        Product product = toEntity(productRequestDto);
        Product savedProduct = productRepository.addProduct(product);
        return toDto(savedProduct);
    }

    private Product toEntity(ProductRequestDto dto) {
        return new Product(dto.getName(), dto.getPrice(), dto.getImageUrl());
    }

    private ProductResponseDto toDto(Product product) {
        return new ProductResponseDto(product.getId(), product.getName(), product.getPrice(), product.getImageUrl());
    }


    public ProductResponseDto findProduct(Long id){
        Product product = productRepository.findProduct(id);
        if(product==null){
            throw new NoSuchElementException("product does not exist.");
        }
        return new ProductResponseDto(product.getId(), product.getName(),product.getPrice(),product.getImageUrl());
    }

    public ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto){
        Product product = productRepository.findProduct(id);
        if(product==null){
            throw new NoSuchElementException("product does not exist.");
        }
        //db저장
        product.updateProduct(productRequestDto.getName(),productRequestDto.getPrice(),productRequestDto.getImageUrl());
        Product updateProduct = productRepository.updateProduct(product);

        return new ProductResponseDto(updateProduct.getId(),updateProduct.getName(),updateProduct.getPrice(),updateProduct.getImageUrl());
    }

    public void deleteProduct(Long id){
        Product product = productRepository.findProduct(id);
        if(product==null){
            throw new NoSuchElementException("Product does not exist.");
        }
        productRepository.deleteProduct(id);
    }
}
