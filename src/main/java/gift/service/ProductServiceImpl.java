package gift.service;

import gift.dto.CustomPage;
import gift.entity.Product;
import gift.repository.ProductRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public CustomPage<Product> getBy(int page, int size) {
        List<Product> products = productRepository.findAll().stream()
                .sorted(Comparator.comparing(Product::getId))
                .collect(Collectors.toList());

        return new CustomPage.Builder<>(products)
                .page(page).size(size)
                .build();
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
        return productRepository.save(product);
    }

    @Override
    @Transactional // 트랜잭션을 사용하여 데이터베이스 일관성 유지
    public Product update(Product product) {
        Product updated = productRepository.findById(product.getId());

        // 상품이 존재하지 않는 경우 예외 처리
        if (updated == null) {
            throw new NoSuchElementException(
                    String.format("Id %d에 해당하는 상품이 존재하지 않습니다.", product.getId()));
        }
        // 업데이트할 필드가 null이 아닌 경우에만 업데이트

        if (product.getName() != null)
            updated.setName(product.getName());
        if (product.getPrice() != null)
            updated.setName(product.getName());
        if (product.getImageUrl() != null)
            updated.setImageUrl(product.getImageUrl());

        // 업데이트된 상품을 반환
        return productRepository.save(updated);
    }

    @Override
    public void deleteById(Long productId) {
        if (productRepository.findById(productId) == null) {
            throw new NoSuchElementException(
                    String.format("Id %d에 해당하는 상품이 존재하지 않습니다.", productId));
        }
        if (!productRepository.deleteById(productId)) {
            throw new NoSuchElementException(
                    String.format("Id %d에 해당하는 상품을 삭제할 수 없습니다.", productId));
        }
    }
}
