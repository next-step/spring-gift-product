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

    public Product updateProduct(Long id, Product updateData) {
        ProductValidator.validate(updateData);                     // 유효성 검사
        Product existing = ProductValidator.validateExists(id, repository); // 존재 여부 확인

        repository.update(id, updateData);
        return existing;
    }

    public void deleteProduct(Long id) {
        ProductValidator.validateExists(id, repository); // 존재 여부 검증
        repository.delete(id);
    }

}
