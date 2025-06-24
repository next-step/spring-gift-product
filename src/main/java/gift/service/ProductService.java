package gift.service;

import gift.dto.request.ProductRequestDto;
import gift.entity.Product;
import gift.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service

public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(ProductRequestDto requestDto) {
        Product product = new Product(null, requestDto.getName(), requestDto.getPrice(), requestDto.getImageUrl());
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


    public Product updateProduct(Long id,ProductRequestDto requestDto) {
        Product updated =productRepository.findById(id) .orElseThrow(() -> new NoSuchElementException("해당 상품이 존재하지 않습니다: id=" + id));
        ;
        updated.setName(requestDto.getName());
        updated.setPrice(requestDto.getPrice());
        updated.setImageUrl(requestDto.getImageUrl());

        return productRepository.save(updated);
    }


    public void deleteProduct(Long id) {
        Product updated =productRepository.findById(id) .orElseThrow(() -> new NoSuchElementException("해당 상품이 존재하지 않습니다: id=" + id));
        productRepository.deleteById(updated.getId());
    }

}
