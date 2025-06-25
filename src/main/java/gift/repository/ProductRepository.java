package gift.repository;

import gift.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {

    private final List<Product> products = new ArrayList<>();

    public Product findById(Long id) {
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (product.id().equals(id))
                return product;
        }
        return null;
    }

    public Product saveProduct(Product product) {
        products.add(product);
        return product;
    }

    public Product updateProduct(Long id,Product product) {
        for(int i = 0; i < products.size(); i++) {
            if(product.id().equals(id)) {
                products.set(i, product);
                return product;
            }
        }
        return null;
    }
}
