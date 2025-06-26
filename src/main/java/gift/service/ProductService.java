package gift.service;

import gift.dto.request.CreateProductDto;
import gift.dto.request.UpdateProductDto;
import gift.dto.response.ProductDto;
import gift.entity.Product;
import gift.exception.EntityNotFoundException;
import gift.repository.ProductRepository;
import gift.validator.CreateProductValidator;
import gift.validator.UpdateProductValidator;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CreateProductValidator createProductValidator;
    private final UpdateProductValidator updateProductValidator;

    public ProductService(ProductRepository productRepository,
                          CreateProductValidator createProductValidator,
                          UpdateProductValidator updateProductValidator) {
        this.productRepository = productRepository;
        this.createProductValidator = createProductValidator;
        this.updateProductValidator = updateProductValidator;
    }

    public ProductDto createProduct(CreateProductDto body) {
        createProductValidator.validate(body);
        Product instance = Product.build(body);
        Product created = productRepository.save(instance);
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
        updateProductValidator.validate(body);
        Product instance = Product.build(body);
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
