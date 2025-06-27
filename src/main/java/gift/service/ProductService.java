package gift.service;

import gift.common.exception.ProductNotFoundException;
import gift.domain.Product;
import gift.dto.product.CreateProductRequest;
import gift.dto.product.UpdateProductRequest;
import gift.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product saveProduct(CreateProductRequest request) {
        Product product = new Product(request.name(), request.price(), request.quantity());
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(Long id) {
        return getById(id);
    }

    public Product updateProduct(Long id, UpdateProductRequest request) {
        Product product = getById(id);
        product.update(request.name(), request.price(), request.quantity());
        productRepository.update(product);
        return product;
    }

    public void deleteProduct(Long id) {
        productRepository.deleteByid(id);
    }


    private Product getById(Long id) {
        Optional<Product> getProduct = productRepository.findById(id);
        return getProduct.orElseThrow(ProductNotFoundException::new);
    }
}
