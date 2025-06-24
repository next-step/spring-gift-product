package gift.service;

import gift.model.Product;
import gift.repository.ProductRepository;
import java.util.List;
import java.util.Optional;

public class ProductService {

    private final ProductRepository productRepository = new ProductRepository();

    // 상품 저장
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    // 상품 전체 조회
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // 상품 단건 조회
    public Product getProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new IllegalArgumentException("해당 ID의 상품이 존재하지 않습니다.");
        }
        return product.get();
    }
}
