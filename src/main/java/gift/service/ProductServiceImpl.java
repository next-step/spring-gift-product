package gift.service;

import gift.model.Product;
import gift.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product createProduct(Product product) {
        if (!validateProduct(product)) {
            throw new IllegalArgumentException("상품 데이터가 유효하지 않습니다.");
        }
        if (product.getId() != null && productRepository.existsById(product.getId())) {
            throw new IllegalArgumentException("이미 존재하는 상품 ID입니다: " + product.getId());
        }
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Optional<Product> existingProductOptional = productRepository.findById(id);
        if (existingProductOptional.isEmpty()) {
            throw new IllegalArgumentException("상품을 찾을 수 없습니다: " + id);
        }
        if (!id.equals(product.getId())) {
            throw new IllegalArgumentException("URL ID와 요청 본문 ID가 일치하지 않습니다.");
        }
        if (!validateProduct(product)) {
            throw new IllegalArgumentException("상품 데이터가 유효하지 않습니다.");
        }
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new IllegalArgumentException("삭제할 상품을 찾을 수 없습니다: " + id);
        }
        productRepository.deleteById(id);
    }

    private boolean validateProduct(Product product) {
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            System.err.println("유효성 검증 실패: 상품 이름은 필수 입력 항목입니다.");
            return false;
        }
        if (product.getPrice() <= 0) {
            System.err.println("유효성 검증 실패: 상품 가격은 0보다 커야 합니다.");
            return false;
        }
        String imageUrl = product.getImageUrl();
        if (imageUrl == null || imageUrl.trim().isEmpty()) {
            System.err.println("유효성 검증 실패: 상품 이미지 URL은 필수 입력 항목입니다.");
            return false;
        }
        String urlRegex = "^(https?|ftp)://[^\\s/$.?#].[^\\s]*$";
        if (!imageUrl.matches(urlRegex)) {
            System.err.println("유효성 검증 실패: 상품 이미지 URL 형식이 올바르지 않습니다.");
            return false;
        }
        return true;
    }
}
