package gift.repository;

import gift.domain.Product;
import gift.dto.CreateProductRequest;
import gift.dto.UpdateProductRequest;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductMemoryRepository implements ProductRepository{

    private final Map<Long, Product> products = new HashMap<>();
    static Long sequence = 0L;

    @Override
    public Product save(CreateProductRequest request) {
        Long id = ++sequence;
        Product product = new Product(id, request.name(), request.price(), request.imageUrl());
        products.put(id, product);
        return product;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(products.get(id));
    }

    @Override
    public List<Product> findAll() {
        List<Product> productList = new ArrayList<>();
        Collection<Product> values = products.values();
        for (Product value : values) {
            productList.add(value);
        }
        return productList;
    }

    @Override
    public Product update(Long id, UpdateProductRequest request) {
        Product updateProduct = new Product(id, request.name(), request.price(), request.imageUrl());
        products.remove(id);
        products.put(id, updateProduct);
        return updateProduct;
    }

    @Override
    public void delete(Long id) {
        products.remove(id);
    }
}
