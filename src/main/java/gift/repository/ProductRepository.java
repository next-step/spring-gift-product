package gift.repository;
import gift.entity.Product;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class ProductRepository {

    // id 지정하는 변수
    private static Long label = 1L;

    // DB 대체 제품 저장 컬렉션
    private final Map<Long, Product> products = new HashMap<>();

    public Optional<Product> findProductById(long id) {
        Product product = products.get(id);
        return Optional.ofNullable(product);
    }

    public List<Product> findAllProducts() {
        return new ArrayList<>(products.values());
    }

    public Product saveProduct(Product product) {
        Product newProduct = new Product(label, product.name(), product.price(), product.imageUrl());
        products.put(label++, newProduct);
        return newProduct;
    }

    public boolean updateProduct(Long id, Product product) {
        Product newProduct = new Product(id, product.name(), product.price(), product.imageUrl());
        return products.replace(id, newProduct) != null;
    }

    public boolean deleteProduct(Long id) {
        return products.remove(id) != null;
    }
}
