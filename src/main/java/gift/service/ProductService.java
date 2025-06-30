package gift.service;

import gift.dto.ProductRequestDTO;
import gift.dto.ProductResponseDTO;
import gift.entity.Product;
import gift.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductResponseDTO> getAll() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> new ProductResponseDTO(
                        product.getId(),
                        product.getName(),
                        product.getPrice(),
                        product.getImageUrl()
                ))
                .collect(Collectors.toList());
    }

    public ProductResponseDTO getById(Integer id) {
        Product product = productRepository.findById(id);
        if (product == null) {
            throw new IllegalArgumentException("Product not found");
        }
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getImageUrl()
        );
    }

    public ProductResponseDTO create(ProductRequestDTO productRequestDTO) {
        Product product = new Product(
                null,
                productRequestDTO.name(),
                productRequestDTO.price(),
                productRequestDTO.imageUrl()
        );

        Product saved = productRepository.save(product);

        return new ProductResponseDTO(
                null,
                saved.getName(),
                saved.getPrice(),
                saved.getImageUrl()
        );
    }

    public ProductResponseDTO update(Integer id, ProductRequestDTO productRequestDTO) {
        Product product = productRepository.findById(id);
        if (product == null) {
            throw new IllegalArgumentException("Product not found");
        }

        Product updated = new Product(
                id,
                productRequestDTO.name(),
                productRequestDTO.price(),
                productRequestDTO.imageUrl()
        );

        productRepository.update(id, updated);

        return new ProductResponseDTO(
                updated.getId(),
                updated.getName(),
                updated.getPrice(),
                updated.getImageUrl()
        );
    }

    public void delete(Integer id) {
        Product product = productRepository.findById(id);
        if (product == null) {
            throw new IllegalArgumentException("Product not found");
        }
        productRepository.delete(id);
    }
}
