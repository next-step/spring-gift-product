package gift.service;

import gift.dto.ProductRequestDto;
import gift.entity.Product;
import gift.repository.ProductRepository;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }


    public Product save(ProductRequestDto dto) {
        Product product = dto.toEntity();
        return productRepository.save(product);
    }

    public void update(Long id, ProductRequestDto dto) {
        Product updated = dto.toEntity();
        productRepository.update(id, updated);
    }

    public Product findById(Long id) {
        Product product = productRepository.findById(id);
        if (product == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "상품(id=" + id + ")을 찾을 수 없습니다."
            );
        }
        return product;
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }


}
