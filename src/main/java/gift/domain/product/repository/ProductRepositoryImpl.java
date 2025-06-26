package gift.domain.product.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import gift.common.pagination.Page;
import gift.common.pagination.Pageable;
import gift.common.pagination.PageImpl;
import org.springframework.stereotype.Repository;

import gift.domain.product.model.Product;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    
    private final Map<Long, Product> products = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong();

    public Page<Product> findAll(Pageable pageable) {
        List<Product> productList = new ArrayList<>(products.values());
        productList.sort(Comparator.comparing(Product::getId));

        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getSize(), productList.size());

        if (start > productList.size()) {
            return new PageImpl<>(Collections.emptyList(), pageable, productList.size());
        }

        List<Product> content = productList.subList(start, end);

        return new PageImpl<>(content, pageable, productList.size());
    }

    public Product findById(Long id) {
        return products.get(id);
    }

    public Product save(Product product) {
        if (product.getId() != null) {
            products.put(product.getId(),product);
        }
        long id = sequence.incrementAndGet();
        Product newProduct = new Product(id, product.getName(), product.getPrice(), product.getImageUrl());
        products.put(id, newProduct);
        return newProduct;
    }

    public void deleteById(Long id) {
        products.remove(id);
    }

    public boolean existsById(Long id) {
        return products.containsKey(id);
    }
    
}
