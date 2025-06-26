package gift.service;

import gift.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private final List<Product> products = new ArrayList<>();
    public ProductService() {
    }
    public List<Product> getAllProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Long id) {
        products.removeIf(product -> product.getId().equals(id));
    }

    public void updateProduct(Long id, Product product) {
        for (Product p : products) {
            if (p.getId().equals(id)) {
                if (product.getName() != null) p.setName(product.getName());
                if (product.getPrice() != null) p.setPrice(product.getPrice());
                if (product.getImage() != null) p.setImage(product.getImage());
                return;
            }
        }
    }
}
