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
        product.updateFromProductRequestDTO(dto);
        return new ProductResponseDTO(productRepository.create(product));
    }

    public Optional<ProductResponseDTO> update(Long id, ProductRequestDTO dto) {
        return productRepository.findById(id).map(product -> {
            product.updateFromProductRequestDTO(dto);
            return productRepository.update(product);
        }).map(ProductResponseDTO::new);
    }

    public Optional<ProductResponseDTO> findProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(ProductResponseDTO::new);
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
