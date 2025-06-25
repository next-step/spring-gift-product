package gift.product.service;

import gift.exception.EntityNotFoundException;
import gift.product.dto.ProductCreateRequestDto;
import gift.product.dto.ProductResponseDto;
import gift.product.dto.ProductUpdateRequestDto;
import gift.product.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductService {

    private final ConcurrentHashMap<Long, Product> products = new ConcurrentHashMap<>();
    private final AtomicLong nextId = new AtomicLong(0);

    private void validateProduct(Product product) {
        if (product == null) {
            throw new EntityNotFoundException("해당 상품을 찾을 수 없습니다.");
        }
    }

    public ProductResponseDto createProduct(ProductCreateRequestDto product) {
        final long newProductId = nextId.incrementAndGet();

        Product newProduct = new Product(newProductId, product.name(), product.price(), product.imageUrl());
        products.put(newProductId, newProduct);
        return new ProductResponseDto(newProduct);
    }

    public ProductResponseDto updateProduct(Long id, ProductUpdateRequestDto product) {
        Product existingProduct = products.get(id);
        validateProduct(existingProduct);

        synchronized (existingProduct) {
            existingProduct.update(product.name(), product.price(), product.imageUrl());
            return new ProductResponseDto(existingProduct);
        }
    }

    public void deleteProduct(Long id) {
        Product removedProduct = products.remove(id);
        validateProduct(removedProduct);
    }

    public List<ProductResponseDto> getProducts() {
        return products.values().stream()
                .map(ProductResponseDto::new)
                .toList();
    }

    public ProductResponseDto getProduct(Long id) {
        Product product = products.get(id);
        validateProduct(product);
        return new ProductResponseDto(product);
    }
}
