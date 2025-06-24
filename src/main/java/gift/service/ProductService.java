package gift.service;

import gift.dto.ProductRequestDTO;
import gift.dto.ProductResponseDTO;
import gift.entity.Product;
import gift.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponseDTO create(ProductRequestDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setImageUrl(dto.getImageUrl());
        return new ProductResponseDTO(productRepository.create(product));
    }

    public ProductResponseDTO update(Long id, ProductResponseDTO dto) {
        Product product = productRepository.findById(id);
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "id를 찾을 수 없습니다.");
        }
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setImageUrl(dto.getImageUrl());
        return new ProductResponseDTO(productRepository.update(product));
    }

    public ProductResponseDTO findProductById(Long id) {
        Product product = productRepository.findById(id);
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "id를 찾을 수 없습니다.");
        }
        return new ProductResponseDTO(product);
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public void deleteProductById(Long id) {
        Product product = productRepository.deleteById(id);
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "id를 찾을 수 없습니다.");
        }
    }
}
