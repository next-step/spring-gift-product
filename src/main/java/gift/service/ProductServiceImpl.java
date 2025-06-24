package gift.service;

import gift.entity.Product;
import gift.repository.ProductRepository;
import org.springframework.stereotype.Repository;

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
}
