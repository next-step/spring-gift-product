package gift.product.repository;

import gift.product.Product;
import gift.product.dto.ProductRequest;
import gift.product.dto.ProductUpdateRequest;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductRepository {
    private final Map<Long, Product> products = new HashMap<>();

    private static Long nextId = 1L;

    public Optional<Product> save(ProductRequest request){
        if (products.containsKey(nextId)){
            return Optional.empty();
        }
        Product product = new Product(nextId, request.name(), request.price(), request.imageUrl());
        products.put(nextId, product);
        nextId = nextId + 1;
        return Optional.of(product);
    }

    public Optional<Product> get(Long id) {
        if (products.containsKey(nextId)){
            return Optional.empty();
        }
        return Optional.of(products.get(id));
    }

    public void delete(Long id){
        products.remove(id);
    }

    public Optional<List<Product>> getAll(){
        List<Product> productList = new ArrayList<>(products.values());
        return Optional.of(productList);
    }
}
