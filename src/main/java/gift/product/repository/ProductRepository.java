package gift.product.repository;

import gift.common.exception.ErrorCode;
import gift.product.domain.Product;
import gift.product.dto.CreateProductReqDto;
import gift.product.dto.UpdateProductReqDto;
import gift.product.exception.ProductNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductRepository {

    private final ConcurrentHashMap<Long, Product> productMap = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    public List<Product> findAll() {
        return new ArrayList<>(productMap.values());
    }

    public Product findById(Long id) throws ProductNotFoundException {
        Product product = productMap.get(id);
        if (product == null) {
            throw new ProductNotFoundException(ErrorCode.NOT_FOUND);
        }
        return product;
    }

    public Long save(CreateProductReqDto dto) {
        Long id = idGenerator.incrementAndGet();
        Product newProduct = new Product(id, dto.name(), dto.price(), dto.description());
        productMap.put(id, newProduct);
        return id;
    }

    public void update(Long id, Product updatedProduct) {
        productMap.put(id, updatedProduct);
    }

    public boolean delete(Long id) {
        return productMap.remove(id) != null;
    }


}
