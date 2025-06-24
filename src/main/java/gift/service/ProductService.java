package gift.service;

import gift.dto.ProductRequestDto;
import gift.entity.Product;
import gift.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id){
        return productRepository.findById(id);
    }

    public Product create(ProductRequestDto dto){
        return productRepository.save(
                new Product(null, dto.getName(), dto.getPrice(), dto.getImageUrl()));
    }

    public Optional<Product> update(Long id, ProductRequestDto dto){
        if(productRepository.findById(id).isEmpty()){
            return Optional.empty();
        }

        Product product = new Product(id, dto.getName(), dto.getPrice(), dto.getImageUrl());
        productRepository.update(id, product);
        return Optional.of(product);
    }

    public boolean delete(Long id){
        if(productRepository.findById(id).isEmpty()){
            return false;
        } else {
            productRepository.delete(id);
            return true;
        }
    }

}
