package gift.repository;

import gift.dto.ProductResponseDto;
import gift.entity.Product;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepository {

    // id 지정하는 변수
    private static Long label = 1L;

    // DB 대체 제품 저장 컬렉션
    private final Map<Long, Product> products = new HashMap<>();

    public Product findProductById(long id) {
        Product product = products.get(id);
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No found Product");
        }
        return product;
    }

    public List<ProductResponseDto> findAllProducts() {
        List<ProductResponseDto> list = new ArrayList<>();
        for(Product product : products.values()){
            list.add(new ProductResponseDto(product));
        }
        return list;
    }

    public ProductResponseDto saveProduct(Product product) {
        product.setId(label);
        products.put(label, product);
        label++;
        return new ProductResponseDto(product);
    }

    public boolean updateProduct(Long id, String name, Long price) {
        Product product = new Product(id, name, price);
        return products.replace(id, product) != null;
    }

    public boolean deleteProduct(Long id) {
        return products.remove(id) != null;
    }
}
