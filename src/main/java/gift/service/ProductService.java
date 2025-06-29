package gift.service;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.exception.NotFoundByIdException;
import gift.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Long saveProduct(ProductRequestDto productRequestDto) {
        return productRepository.saveProduct(
            productRequestDto.name(),
            productRequestDto.price(),
            productRequestDto.imageUrl()
        );
    }

    public void deleteProductById(Long id) {
        productRepository.deleteProductById(id);
    }

    public void updateProduct(Long id, ProductRequestDto productUpdateDto) {
        boolean isSuccessful = productRepository.updateProduct(
                productUpdateDto.toEntity(id)
        );
        if (!isSuccessful)
            throw new NotFoundByIdException("Not found by id: " + id);
    }

    public List<ProductResponseDto> findAllProducts() {
        return productRepository.findAllProducts().stream()
                .map(ProductResponseDto::new)
                .toList();
    }

    public ProductResponseDto findProductById(Long id) {
        return productRepository.findProductById(id)
                .map(ProductResponseDto::new)
                .orElseThrow(() -> new NotFoundByIdException("Not Found by id: " + id));
    }
}
