package gift.service;

import gift.entity.Product;
import gift.repository.ProductRepository;
import gift.repository.ProductRepositoryImpl;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getById(Long productId) {
        Optional<Product> product = Optional.ofNullable(productRepository.findById(productId));

        return product.orElseThrow(() ->
                new NoSuchElementException(
                        String.format("Id %d에 해당하는 상품이 존재하지 않습니다.", productId)
                ));
    }

    @Override
    public Product create(Product product) {
        if (product.getName() == null || product.getName().isEmpty()) {
            throw new IllegalArgumentException("상품 이름은 필수입니다.");
        }
        if (product.getPrice() == null || product.getPrice() <= 0) {
            throw new IllegalArgumentException("상품 가격은 0보다 커야 합니다.");
        }
        if (product.getImageUrl() == null || product.getImageUrl().isEmpty()) {
            throw new IllegalArgumentException("상품 이미지 URL은 필수입니다.");
        }
        return productRepository.save(product);
    }

    @Override
    @Transactional // 트랜잭션을 사용하여 데이터베이스 일관성 유지
    public Product update(Product product) {
        Product existingProduct = productRepository.findById(product.getId());

        // 상품이 존재하지 않는 경우 예외 처리
        if (existingProduct == null) {
            throw new NoSuchElementException(
                    String.format("Id %d에 해당하는 상품이 존재하지 않습니다.", product.getId()));
        }

        // 유효성 검사
        if (product.getName() != null && product.getName().isEmpty()) {
            throw new IllegalArgumentException("상품 이름은 비워둘 수 없습니다.");
        }
        if (product.getPrice() != null && product.getPrice() <= 0) {
            throw new IllegalArgumentException("상품 가격은 0보다 커야 합니다.");
        }
        if (product.getImageUrl() != null && product.getImageUrl().isEmpty()) {
            throw new IllegalArgumentException("상품 이미지 URL은 비워둘 수 없습니다.");
        }

        if (product.getName() != null) existingProduct.setName(product.getName());
        if (product.getPrice() != null) existingProduct.setPrice(product.getPrice());
        if (product.getImageUrl() != null) existingProduct.setImageUrl(product.getImageUrl());

        return productRepository.update(existingProduct);
    }
}
