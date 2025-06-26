package gift.service;

import gift.entity.Product;
import gift.repository.ProductRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }


    public Product save(Product product){
        return productRepository.save(product);
    }

    public void update(Long id, Product updatedProduct) {
        productRepository.update(id, updatedProduct);
    }

    public Product findById(Long id){
        return productRepository.findById(id);
    }

    public void deleteProductById(Long id){
        productRepository.deleteById(id);
    }


}
