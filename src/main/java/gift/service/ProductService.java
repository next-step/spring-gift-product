package gift.service;

import gift.entity.Product;
import gift.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(String name, Integer price, String imageUrl) {
        Product product = new Product(name, price, imageUrl);
        return productRepository.saveNewProduct(product);
    }

    public Product getProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.getProductById(id);
        if (optionalProduct.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        return optionalProduct.get();
    }
}
