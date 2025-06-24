package gift.product.service;

import gift.exception.ProductNotFoundException;
import gift.product.dto.ProductCreateRequestDto;
import gift.product.dto.ProductUpdateRequestDto;
import gift.product.entity.Product;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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
        if (product.name() != null) {
            existingProduct.setName(product.name());
        }
        if (product.price() != null) {
            existingProduct.setPrice(product.price());
        }
        if (product.imageUrl() != null) {
            existingProduct.setImageUrl(product.imageUrl());
        }
    }

    public void deleteProduct(Long id) {
        if (!products.containsKey(id))
            throw new ProductNotFoundException("해당 상품을 찾을 수 없습니다.");

        products.remove(id);
    }
}
