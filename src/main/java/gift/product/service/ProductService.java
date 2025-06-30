package gift.product.service;

import gift.product.domain.Product;
import gift.product.dto.ProductDto;
import gift.product.repository.ProductDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private ProductDao productdao;

    public ProductService(ProductDao productdao) {
        this.productdao = productdao;
    }

    @Transactional
    public ProductDto saveProduct(ProductDto productdto) {
        if(productdto.getPrice() < 0) {
            throw new IllegalArgumentException("Price should be positive");
        }
        productdao.save(productdto);
        return productdto;
    }

    @Transactional(readOnly = true)
    public List<ProductDto> findAll() {
        return productdao.findAll().stream()
                .map(ProductDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<Product> adminFindAll() {
        return productdao.findAll();
    }

    @Transactional(readOnly = true)
    public ProductDto findById(String id) {
        return new ProductDto(productdao.findById(id));
    }

    @Transactional(readOnly = true)
    public Product adminFindById(String id) {
        return productdao.findById(id);
    }

    @Transactional
    public void updateProduct(String id, ProductDto productdto) {
        if(productdto.getPrice() < 0) {
            throw new IllegalArgumentException("Price should be positive");
        }
        productdao.findById(id);
        productdao.update(id, productdto);
    }

    @Transactional
    public ProductDto deleteProduct(String id) {
        Product product = productdao.findById(id);
        productdao.delete(id);
        return new ProductDto(product);
    }
}
