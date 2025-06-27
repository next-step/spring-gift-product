package gift.service;

import gift.dto.request.CreateProductDto;
import gift.dto.request.UpdateProductDto;
import gift.dto.response.ProductDto;
import gift.entity.Product;
import gift.exception.EntityNotFoundException;
import gift.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDto createProduct(CreateProductDto body) {
        Product instance = body.toEntity();
        Product created = productRepository.save(instance).get();
        return ProductDto.from(created);
    }

    public ProductDto getProduct(Long id) {
        Product result = findProduct(id);
        return ProductDto.from(result);
    }

    public List<ProductDto> getAllProduct() {
        return productRepository.findAll().stream()
                .sorted(Comparator.comparing(Product::getId))
                .map(ProductDto::from)
                .toList();
    }

    public ProductDto updateProduct(Long id, UpdateProductDto body) {
        findProduct(id);
        Product instance = body.toEntity();
        Product product = productRepository.update(id, instance).get();
        return ProductDto.from(product);
    }

    public void deleteProduct(Long id) {
        findProduct(id);
        productRepository.delete(id);
    }

    private Product findProduct(Long id) {
        var result = productRepository.findById(id);
        if (result.isEmpty()) {
            throw new EntityNotFoundException("Product id {"+id+"} not found");
        }
        return result.get();
    }
}
