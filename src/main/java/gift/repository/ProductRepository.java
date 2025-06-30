package gift.repository;

import gift.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

@Repository
public class ProductRepository {
    private final Map<Integer, Product> store = new HashMap<>();
    private Integer nextId = 1;
    private final ReentrantLock lock = new ReentrantLock();

    public Product save(Product product) {
        lock.lock();
        try {
            Product toSave = new Product(nextId++, product.getName(), product.getPrice(), product.getImageUrl());
            store.put(toSave.getId(), toSave);
            return toSave;
        } finally {
            lock.unlock();
        }
    }

    public List<Product> findAll() {
        lock.lock();
        try {
            return new ArrayList<>(store.values());
        } finally {
            lock.unlock();
        }
    }

    public Product findById(Integer id) {
        lock.lock();
        try {
            return store.get(id);
        } finally {
            lock.unlock();
        }
    }

    public void delete(Integer id) {
        lock.lock();
        try {
            store.remove(id);
        } finally {
            lock.unlock();
        }
    }
}
