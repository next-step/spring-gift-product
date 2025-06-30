package gift.service;

import gift.entity.Product;
import gift.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(String name, Integer price, String imageUrl) {
        Product product = new Product(name, price, imageUrl);
        Optional<Long> optionalId = productRepository.saveNewProduct(product);
        if (optionalId.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Product creation failed");
        }
        product.setId(optionalId.get());
        return product;
    }

    public Product getProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.getProductById(id);
        throwNotFoundIfTrue(optionalProduct.isEmpty());
        return optionalProduct.get();
    }

    public List<Product> getProductList() {
        return productRepository.getProductList();
    }

    public Product updateSelectivelyProductById(Long id, String name, Integer price, String imageUrl) {
        Optional<Product> optionalProduct = productRepository.getProductById(id);
        throwNotFoundIfTrue(optionalProduct.isEmpty());
        if (name == null && price == null && imageUrl == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정할 요소가 한개 이상은 작성되어야 합니다");
        }
        Product product = optionalProduct.get();
        if (name != null) {
            product.setName(name);
        }
        if (price != null) {
            product.setPrice(price);
        }
        if (imageUrl != null) {
            product.setImageUrl(imageUrl);
        }
        throwNotFoundIfTrue(productRepository.updateProduct(product) == 1);
        return product;
    }

    public Product updateProductById(Long id, String name, Integer price, String imageUrl) {
        Product product = new Product(id, name, price, imageUrl);
        throwNotFoundIfTrue(productRepository.updateProduct(product) == 1);
        return product;
    }

    public void deleteProductById(Long id) {
        throwNotFoundIfTrue(productRepository.deleteProductById(id) == 1);
    }

    private void throwNotFoundIfTrue(boolean condition) {
        if (condition) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
