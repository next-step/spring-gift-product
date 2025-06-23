package gift.service;

import gift.entity.Product;

import gift.repository.ProductRepository;
import gift.validator.ProductValidator;
import java.util.List;

public class ProductService {

    private final ProductRepository repository = new ProductRepository();

    public Product registerProduct(Product product) {
        ProductValidator.validate(product);  // 검증은 여기서 호출만

        return repository.save(product);
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product getProductById(Long id) {
        return ProductValidator.validateExists(id, repository);
    }

}
