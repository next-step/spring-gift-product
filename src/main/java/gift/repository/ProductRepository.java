package gift.repository;

import gift.dto.ProductResponseDto;
import gift.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepository {
    private final Map<Long, Product> products = new HashMap<>();

    private Long idCounter = 1L;

    public Product saveProduct(Product product) {
        product.setId(idCounter++);
        products.put(product.getId(), product);
        return product;
    }

    public List<ProductResponseDto> findAllProducts() {
        List<ProductResponseDto> allProducts = new ArrayList<>();
        for (Product product : products.values()) {
            ProductResponseDto responseDto = new ProductResponseDto(product);
            allProducts.add(responseDto);
        }

        return allProducts;
    }

    public Product findProductById(Long id) {
        return products.get(id);
    }
}
