package gift.repository;

import gift.entity.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

    private final Map<Long, Product> products = new HashMap<>();
    private long tempId = 1L;

    // 전체 조회
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    // 단건 조회
    public Product findById(Long id) {
        return products.get(id);
    }

    // 등록
    public Product createProduct(Product product) {
        product.setId(tempId++);
        products.put(product.getId(), product);
        return product;
    }

    // 수정
    public Product updateProduct(Long id, Product updatedProduct) {
        if (!products.containsKey(id)) {
            return null;
        }
        updatedProduct.setId(id);
        products.put(id, updatedProduct);
        return updatedProduct;
    }
}

