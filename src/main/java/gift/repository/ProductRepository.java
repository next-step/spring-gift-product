package gift.repository;

import gift.entity.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductRepository {
    private final Map<Long, Product> store = new HashMap<>();
    private Long nextId = 1L;

    public List<Product> findAll(){
        return new ArrayList<>(store.values());
    }

}
