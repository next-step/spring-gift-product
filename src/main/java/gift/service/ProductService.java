package gift.service;

import gift.entity.Product;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {

    private final Map<Long, Product> products = new HashMap<>();

    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    public Product getProductById(Long id) {
        return products.get(id);
    }

    public Product createProduct(Product productWithoutId) {
        Long productId = products.isEmpty() ? 1 : Collections.max(products.keySet()) + 1;
        Product newProduct = Product.createWithId(productWithoutId, productId);
        products.put(productId, newProduct);

        return newProduct;
    }

    public Product updateProduct(Long id, Product updateRequest) {
        Product product = products.get(id);

        if (product == null) {
            return null;
        }

        product.setName(updateRequest.getName() != null ? updateRequest.getName() : product.getName());
        product.setPrice(updateRequest.getPrice() != 0 ? updateRequest.getPrice() : product.getPrice());
        product.setImageUrl(updateRequest.getImageUrl() != null ? updateRequest.getImageUrl() : product.getImageUrl());

        return product;
    }

    public boolean deleteProduct(Long id) {
        return products.remove(id) != null;
    }
}
