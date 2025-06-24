package gift.repository;

import gift.dto.ProductResponseDto;
import gift.entity.Product;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Repository
public class ProductRepositoryImp implements ProductRepository{
    private final Map<Long, Product> products = new HashMap<>();

    public ProductRepositoryImp(){
        Product product1 = new Product(1L, "test1", 12000L, "temp1");
        Product product2 = new Product(2L, "test2", 13000L, "temp2");
        products.put(product1.getId(), product1);
        products.put(product2.getId(), product2);
    }

    @Override
    public List<Product> findAllProducts() {

        return new ArrayList<>(products.values());
    }

    @Override
    public Product findProductByIdOrElseThrow(Long id) {
        Product product = products.get(id);

        if (product == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 ID의 상품을 찾을 수 없습니다.");
        }

        return product;
    }

    @Override
    public Product createProduct(String name, Long price, String imageUrl) {
        Long productId = products.isEmpty() ? 1 : Collections.max(products.keySet()) + 1;
        Product product = new Product(productId, name, price, imageUrl);
        products.put(productId, product);

        return product;
    }

    @Override
    public void deleteProduct(Long id) {
        products.remove(id);
    }
}
