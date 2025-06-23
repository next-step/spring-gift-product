package gift.service.impl;

import gift.model.Product;
import gift.repository.ProductRepository;
import gift.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        if (!product.validateProduct()) {
            throw new RuntimeException("상품 정보가 올바르지 않습니다.");
        }
        return productRepository.save(product);
    }

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("상품을 찾을 수 없습니다."));
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        if (!product.validateProduct()) {
            throw new RuntimeException("상품 정보가 올바르지 않습니다.");
        }

        if (productRepository.findById(id).isEmpty()) {
            throw new RuntimeException("상품을 찾을 수 없습니다.");
        }

        return productRepository.update(id, product);
    }

    @Override
    public void deleteProduct(Long id) {
        if (productRepository.findById(id).isEmpty()) {
            throw new RuntimeException("상품을 찾을 수 없습니다.");
        }

        productRepository.deleteById(id);
    }
}
