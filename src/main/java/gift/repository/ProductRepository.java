package gift.repository;

import gift.entity.Product;

import java.util.HashMap;
import java.util.Map;

public class ProductRepository {
    private final Map<Long, Product> producsts = new HashMap<>();
}
