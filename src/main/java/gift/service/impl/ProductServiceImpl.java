package gift.service.impl;

import gift.dto.ProductRequestDto;
import gift.model.Product;
import gift.repository.ProductRepository;
import gift.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(ProductRequestDto productDto) {
        Product product = productDto.toEntity();
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("상품을 찾을 수 없습니다. ID: " + id));
    }

    @Override
    public Product updateProduct(Long id, ProductRequestDto productDto) {
        if (productRepository.findById(id).isEmpty()) {
            throw new RuntimeException("상품을 찾을 수 없습니다. ID: " + id);
        }
        return productRepository.update(id, productDto);
    }

    @Override
    public void deleteProduct(Long id) {
        if (productRepository.findById(id).isEmpty()) {
            throw new RuntimeException("상품을 찾을 수 없습니다. ID: " + id);
        }

        productRepository.deleteById(id);
    }
}
