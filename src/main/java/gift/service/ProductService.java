package gift.service;

import gift.model.Product;
import gift.repository.ProductRepository;

public class ProductService {

    private final ProductRepository productRepository = new ProductRepository();

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }
}
