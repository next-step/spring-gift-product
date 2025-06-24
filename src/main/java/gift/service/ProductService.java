package gift.service;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import gift.repository.ProductRepository;

import java.util.NoSuchElementException;

public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public ProductResponseDto addProduct(ProductRequestDto productRequestDto){
        Product product = new Product( //Product로 만드는게 낫다
                productRequestDto.getName(),
                productRequestDto.getPrice(),
                productRequestDto.getImageUrl()

        );
        Product saveProduct = productRepository.addProduct(product);
        return new ProductResponseDto(
                saveProduct.getId(),
                saveProduct.getName(),
                saveProduct.getPrice(),
                saveProduct.getImageUrl()
        );
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
