package gift.service;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.entity.Product;
import gift.exception.NotFoundByIdException;
import gift.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        productRepository.updateProduct(
                productUpdateDto.toEntity(id)
        );
    }

    public List<ProductResponseDto> findAllProducts() {
        return productRepository.findAllProducts().stream()
                .map(ProductResponseDto::new)
                .toList();
    }

    public ProductResponseDto findProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findProductById(id);
        if (optionalProduct.isEmpty())
            throw new NotFoundByIdException("Not Found by id: " + id);

        return new ProductResponseDto(
                optionalProduct.get()
        );
    }
}
