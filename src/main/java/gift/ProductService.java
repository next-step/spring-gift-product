package gift;

import gift.dto.CreateProductDto;
import gift.dto.ProductDto;
import gift.dto.UpdateProductDto;
import gift.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductCollector productCollector;

    public ProductService(ProductCollector productCollector) {
        this.productCollector = productCollector;
    }

    public ProductDto createProduct(CreateProductDto body) {
        Product instance = body.createInstance();
        Product created = productCollector.add(instance);
        return ProductDto.from(created);
    }

    public ProductDto getProduct(Long id) {
        Product result = productCollector.get(id);
        if(result == null) {
            throw new EntityNotFoundException("Product id {"+id+"} not found");
        }
        return ProductDto.from(result);
    }

    public List<ProductDto> getAllProduct() {
        return productCollector.getAll().stream()
                .map(ProductDto::from)
                .collect(Collectors.toList());
    }

    public ProductDto updateProduct(Long id, UpdateProductDto body) {
        Product product = productCollector.get(id);
        if(product == null) {
            throw new EntityNotFoundException("Product id {"+id+"} not found");
        }

        if(body.name() != null) {
            product.setName(body.name());
        }
        if(body.price() != null) {
            product.setPrice(body.price());
        }
        return ProductDto.from(product);
    }

    public void deleteProduct(Long id) {
        Product product = productCollector.get(id);
        if(product == null) {
            throw new EntityNotFoundException("Product id {"+id+"} not found");
        }
        productCollector.delete(id);
    }
}
