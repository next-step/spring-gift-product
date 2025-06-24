package gift.repository;

import gift.dto.ProductResponseDto;
import gift.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private final Map<Long, Product> productList = new HashMap<>();


    @Override
    public List<ProductResponseDto> findAllProducts() {
        List<ProductResponseDto> allProducts = new ArrayList<>();

        for (Product product : productList.values()) {
            ProductResponseDto productResponseDto = new ProductResponseDto(product);
            allProducts.add(productResponseDto);
        }
        return allProducts;
    }

    @Override
    public ProductResponseDto saveProduct(Product product) {
        Long productId = productList.isEmpty() ? 1 : Collections.max(productList.keySet()) + 1;
        product.setId(productId);
        productList.put(productId, product);
        return new ProductResponseDto(product);
    }

    @Override
    public Product findProductById(Long id) {
        return productList.get(id);
    }

    @Override
    public Product updateProduct(Long id, String name, Long price, String imageUrl) {
        Product product = productList.get(id);
        product.updateProduct(name, price, imageUrl);
        return product;
    }
}
