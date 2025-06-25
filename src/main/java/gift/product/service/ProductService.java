package gift.product.service;

import gift.exception.ProductNotFoundException;
import gift.product.dto.ProductCreateRequestDto;
import gift.product.dto.ProductResponseDto;
import gift.product.dto.ProductUpdateRequestDto;
import gift.product.entity.Product;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final Map<Long, Product> products = new HashMap<>();
    private long nextId = 0;

    public void createProduct(ProductCreateRequestDto product) {
        final long newProductId = ++nextId;
        products.put(newProductId, new Product(
                newProductId,
                product.name(),
                product.price(),
                product.imageUrl()
        ));
    }

    public void updateProduct(Long id, ProductUpdateRequestDto product) {
        if (!products.containsKey(id))
            throw new ProductNotFoundException("해당 상품을 찾을 수 없습니다.");

        Product existingProduct = products.get(id);
        existingProduct.update(product.name(), product.price(), product.imageUrl());
    }

    public void deleteProduct(Long id) {
        if (!products.containsKey(id))
            throw new ProductNotFoundException("해당 상품을 찾을 수 없습니다.");

        products.remove(id);
    }

    public List<ProductResponseDto> getProducts() {
        return products.values().stream()
                .map(Product::toResponseDto).collect(Collectors.toList());
    }

    public ProductResponseDto getProduct(Long id) {
        if (!products.containsKey(id))
            throw new ProductNotFoundException("해당 상품을 찾을 수 없습니다.");

        return products.get(id).toResponseDto();
    }
}
