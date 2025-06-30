package gift.product.service;

import gift.product.domain.Product;
import gift.product.dto.ProductDto;
import gift.product.repository.ProductDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public List<Product> findAll() {
        return productdao.findAll();
    }

    @Transactional(readOnly = true)
    public Product findById(String id) {
        return productdao.findById(id);
    }

    @Transactional
    public void updateProduct(String id, ProductDto productdto) {
        if(productdto.getPrice() < 0) {
            throw new IllegalArgumentException("Price should be positive");
        }
        Product product = productdao.findById(id);
        productdao.update(id, productdto);
    }

    @Transactional
    public Product deleteProduct(String id) {
        Product product = productdao.findById(id);
        productdao.delete(id);
        return product;
    }
}
